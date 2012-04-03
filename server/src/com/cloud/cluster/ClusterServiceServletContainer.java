// Copyright 2012 Citrix Systems, Inc. Licensed under the
// Apache License, Version 2.0 (the "License"); you may not use this
// file except in compliance with the License.  Citrix Systems, Inc.
// reserves all rights not expressly granted by the License.
// You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// 
// Automatically generated by addcopyright.py at 04/02/2012
package com.cloud.cluster;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpException;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerRegistry;
import org.apache.http.protocol.HttpService;
import org.apache.http.protocol.ResponseConnControl;
import org.apache.http.protocol.ResponseContent;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.protocol.ResponseServer;
import org.apache.log4j.Logger;

import com.cloud.utils.concurrency.NamedThreadFactory;

public class ClusterServiceServletContainer {
	private static final Logger s_logger = Logger.getLogger(ClusterServiceServletContainer.class);
	
	private ListenerThread listenerThread;
	
	public ClusterServiceServletContainer() {
	}
	
	public boolean start(HttpRequestHandler requestHandler, int port) {
	
		listenerThread = new ListenerThread(requestHandler, port);
		listenerThread.start();
		
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public void stop() {
		if(listenerThread != null) {
			listenerThread.interrupt();
			listenerThread.stop();
		}
	}
	
    static class ListenerThread extends Thread {
        private HttpService _httpService = null;
        private ServerSocket _serverSocket = null;
        private HttpParams _params = null;
        private ExecutorService _executor;

        public ListenerThread(HttpRequestHandler requestHandler, int port) {
    		_executor = Executors.newCachedThreadPool(new NamedThreadFactory("Cluster-Listener"));
        	
            try {
                _serverSocket = new ServerSocket(port);
            } catch (IOException ioex) {
                s_logger.error("error initializing cluster service servlet container", ioex);
                return;
            }

            _params = new BasicHttpParams();
            _params
                .setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 5000)
                .setIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 8 * 1024)
                .setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, false)
                .setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true)
                .setParameter(CoreProtocolPNames.ORIGIN_SERVER, "HttpComponents/1.1");

            // Set up the HTTP protocol processor
            BasicHttpProcessor httpproc = new BasicHttpProcessor();
            httpproc.addInterceptor(new ResponseDate());
            httpproc.addInterceptor(new ResponseServer());
            httpproc.addInterceptor(new ResponseContent());
            httpproc.addInterceptor(new ResponseConnControl());

            // Set up request handlers
            HttpRequestHandlerRegistry reqistry = new HttpRequestHandlerRegistry();
            reqistry.register("/clusterservice", requestHandler);

            // Set up the HTTP service
            _httpService = new HttpService(httpproc, new DefaultConnectionReuseStrategy(), new DefaultHttpResponseFactory());
            _httpService.setParams(_params);
            _httpService.setHandlerResolver(reqistry);
        }

        public void run() {
        	if(s_logger.isInfoEnabled())
        		s_logger.info("Cluster service servlet container listening on port " + _serverSocket.getLocalPort());
            
            while (!Thread.interrupted()) {
                try {
                    // Set up HTTP connection
                    Socket socket = _serverSocket.accept();
                    final DefaultHttpServerConnection conn = new DefaultHttpServerConnection();
                    conn.bind(socket, _params);

                    _executor.execute(new Runnable() {
                    	public void run() {
                            HttpContext context = new BasicHttpContext(null);
                            try {
                            	while(!Thread.interrupted() && conn.isOpen()) {
	                            	if(s_logger.isTraceEnabled())
	                            		s_logger.trace("dispatching cluster request from " + conn.getRemoteAddress().toString());
	                            	
	                                _httpService.handleRequest(conn, context);
	                                
	                            	if(s_logger.isTraceEnabled())
	                            		s_logger.trace("Cluster request from " + conn.getRemoteAddress().toString() + " is processed");
                            	}
                            } catch (ConnectionClosedException ex) {
                            	// client close and read time out exceptions are expected
                            	// when KEEP-AVLIE is enabled
                                s_logger.trace("Client closed connection", ex);
                            } catch (IOException ex) {
                                s_logger.trace("I/O error", ex);
                            } catch (HttpException ex) {
                                s_logger.error("Unrecoverable HTTP protocol violation", ex);
                            } finally {
                                try {
                                    conn.shutdown();
                                } catch (IOException ignore) {
                                    s_logger.error("unexpected exception", ignore);
                                }
                            }
                    	}
                    });
                    
                } catch (InterruptedIOException ex) {
                    break;
                } catch (IOException e) {
                    s_logger.error("Exception when initializing cluster service servlet container : ", e);
                    break;
                }
            }
            
            _executor.shutdown();
        	if(s_logger.isInfoEnabled())
        		s_logger.info("Cluster service servlet container shutdown");
        }
    }
}
