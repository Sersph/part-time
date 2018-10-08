import types from '@/store/mutation-types';

export default {
  namespaced: true,
  state: {
    // 搜索兼职条件
    partTimeSearchCondition: {
      cityId: 0,
      areaId: 0,
      typeId: 0,
      speciesId: 0,
      keyword: '',
      limitStart: 0,
      limitSize: 10
    },
    // 搜索兼职结果
    partTimeSearchResultList: [
      { id: 0, type: '派单', title: '运营实习生', location: '罗罗湖区罗湖区罗湖区罗湖区罗湖区罗湖区罗湖区罗湖区罗湖区湖区', viewCount: 233, createdAt: '2011-01-01 11:11' },
      { id: 1, type: '销售', title: '运营实运营实习生运营实习生运营实习生习生', location: '罗湖罗湖区罗湖区区', viewCount: 233, createdAt: '2019-11-11 11:11' },
      { id: 2, type: '临时工', title: '运营运营实习生运营实习生实习生', location: '罗湖区罗湖区', viewCount: 233233233, createdAt: '2019-11-11 11:11' },
      { id: 3, type: '派单', title: '运营运营实习生运营实习生实习生', location: '罗湖区罗湖区', viewCount: 233223323323323323323323323323323323323323323323323323323323323323333233, createdAt: '2019-11-11 11:11' },
      { id: 4, type: '派单派单派单', title: '运营运营实习生运营实习生实习生', location: '罗湖区罗湖区', viewCount: 233233233, createdAt: '2019-11-11 11:11' },
      { id: 5, type: '派单派单派单', title: '运营运营实习生运营实习生实习生', location: '罗湖区罗湖区', viewCount: 233233233, createdAt: '2019-11-11 11:11' },
      { id: 6, type: '派单派单派单', title: '运营运营实习生运营实习生实习生', location: '罗湖区罗湖区', viewCount: 2222222, createdAt: '2019-11-11 11:11' },
      { id: 7, type: '派单', title: '运营运营实运营运营实习生运营实习生实习生运营运营实习生运营实习生实习生习生运营实习生实习生', location: '222', viewCount: 1, createdAt: '2019-09-09 12:12' },
      { id: 8, type: '派单', title: '运营实习生', location: '罗湖区', viewCount: 233, createdAt: '2011-01-01 11:11' },
      { id: 9, type: '派单派单', title: '运营实运营实习生运营实习生运营实习生习生', location: '罗湖罗湖区罗湖区区', viewCount: 233, createdAt: '2019-11-11 11:11' }
    ]
  },
  mutations: {
    [types.EDIT_PART_TIME_SEARCH_CONDITION] (state, { partTimeSearchCondition }) {
      state.partTimeSearchCondition = partTimeSearchCondition;
    }
  },
  actions: {
    editPartTimeSearchCondition ({ commit }, { partTimeSearchCondition }) {
      commit(types.EDIT_PART_TIME_SEARCH_CONDITION, { partTimeSearchCondition });
    },
    async asyncEditPartTimeSearchResultList ({ commit, state }) {
      console.log(JSON.stringify(state.partTimeSearchCondition));
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve();
        }, 200);
      });
    }
  },
  getters: {}
};
