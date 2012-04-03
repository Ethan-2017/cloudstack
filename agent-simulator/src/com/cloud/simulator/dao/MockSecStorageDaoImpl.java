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
package com.cloud.simulator.dao;

import javax.ejb.Local;

import com.cloud.simulator.MockSecStorageVO;
import com.cloud.utils.db.GenericDaoBase;
import com.cloud.utils.db.SearchBuilder;
import com.cloud.utils.db.SearchCriteria;

@Local(value={MockSecStorageDao.class})
public class MockSecStorageDaoImpl extends GenericDaoBase<MockSecStorageVO, Long> implements MockSecStorageDao {
    protected final SearchBuilder<MockSecStorageVO> urlSearch;  
    @Override
    public MockSecStorageVO findByUrl(String url) {
        SearchCriteria<MockSecStorageVO> sc = urlSearch.create();
        sc.setParameters("url", url);
        return findOneBy(sc);
    }
    
    public MockSecStorageDaoImpl() {
        urlSearch = createSearchBuilder();
        urlSearch.and("url", urlSearch.entity().getUrl(), SearchCriteria.Op.EQ);
        urlSearch.done();
    }

}
