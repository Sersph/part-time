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
      { id: 0, type: '派单', title: '运营实习生', location: '罗湖区', viewCount: 233, createdAt: '2011-01-01 11:11', price: '3000', priceType: '月' },
      { id: 0, type: '派单', title: '运营实习生', location: '罗湖区', viewCount: 233, createdAt: '2011-01-01 11:11', price: '3000', priceType: '月' },
      { id: 0, type: '派单', title: '运营实习生', location: '罗湖区', viewCount: 233, createdAt: '2011-01-01 11:11', price: '3000', priceType: '月' },
      { id: 0, type: '派单', title: '运营实习生', location: '罗湖区', viewCount: 233, createdAt: '2011-01-01 11:11', price: '3000', priceType: '月' },
      { id: 0, type: '派单', title: '运营实习生', location: '罗湖区', viewCount: 233, createdAt: '2011-01-01 11:11', price: '3000', priceType: '月' },
      { id: 0, type: '派单', title: '运营实习生', location: '罗湖区', viewCount: 233, createdAt: '2011-01-01 11:11', price: '3000', priceType: '月' },
      { id: 0, type: '派单', title: '运营实习生', location: '罗湖区', viewCount: 233, createdAt: '2011-01-01 11:11', price: '3000', priceType: '月' },
      { id: 0, type: '派单', title: '运营实习生', location: '罗湖区', viewCount: 233, createdAt: '2011-01-01 11:11', price: '3000', priceType: '月' },
      { id: 0, type: '派单', title: '运营实习生', location: '罗湖区', viewCount: 233, createdAt: '2011-01-01 11:11', price: '3000', priceType: '月' },
      { id: 0, type: '派单', title: '运营实习生', location: '罗湖区', viewCount: 233, createdAt: '2011-01-01 11:11', price: '3000', priceType: '月' },
      { id: 0, type: '派单', title: '运营实习生', location: '罗湖区', viewCount: 233, createdAt: '2011-01-01 11:11', price: '3000', priceType: '月' },
      { id: 0, type: '派单', title: '运营实习生', location: '罗湖区', viewCount: 233, createdAt: '2011-01-01 11:11', price: '3000', priceType: '月' },
      { id: 0, type: '派单', title: '运营实习生', location: '罗湖区', viewCount: 233, createdAt: '2011-01-01 11:11', price: '3000', priceType: '月' }
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
