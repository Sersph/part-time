import types from '@/store/mutation-types';
import api from '@/api';

export default {
  namespaced: true,
  state: {
    // 兼职类型, 兼职种类, 薪资类型, 结算类型
    partTimeBaseInfo: [],
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
      {
        id: 0,
        type: '派单',
        title: '运营实习生',
        location: '罗湖区',
        viewCount: 233,
        createdAt: '2011-01-01 11:11',
        price: '3000',
        priceType: '月'
      },
      {
        id: 0,
        type: '派单',
        title: '运营实习生',
        location: '罗湖区',
        viewCount: 233,
        createdAt: '2011-01-01 11:11',
        price: '3000',
        priceType: '月'
      },
      {
        id: 0,
        type: '派单',
        title: '运营实习生',
        location: '罗湖区',
        viewCount: 233,
        createdAt: '2011-01-01 11:11',
        price: '3000',
        priceType: '月'
      },
      {
        id: 0,
        type: '派单',
        title: '运营实习生',
        location: '罗湖区',
        viewCount: 233,
        createdAt: '2011-01-01 11:11',
        price: '3000',
        priceType: '月'
      },
      {
        id: 0,
        type: '派单',
        title: '运营实习生',
        location: '罗湖区',
        viewCount: 233,
        createdAt: '2011-01-01 11:11',
        price: '3000',
        priceType: '月'
      },
      {
        id: 0,
        type: '派单',
        title: '运营实习生',
        location: '罗湖区',
        viewCount: 233,
        createdAt: '2011-01-01 11:11',
        price: '3000',
        priceType: '月'
      },
      {
        id: 0,
        type: '派单',
        title: '运营实习生',
        location: '罗湖区',
        viewCount: 233,
        createdAt: '2011-01-01 11:11',
        price: '3000',
        priceType: '月'
      },
      {
        id: 0,
        type: '派单',
        title: '运营实习生',
        location: '罗湖区',
        viewCount: 233,
        createdAt: '2011-01-01 11:11',
        price: '3000',
        priceType: '月'
      },
      {
        id: 0,
        type: '派单',
        title: '运营实习生',
        location: '罗湖区',
        viewCount: 233,
        createdAt: '2011-01-01 11:11',
        price: '3000',
        priceType: '月'
      },
      {
        id: 0,
        type: '派单',
        title: '运营实习生',
        location: '罗湖区',
        viewCount: 233,
        createdAt: '2011-01-01 11:11',
        price: '3000',
        priceType: '月'
      },
      {
        id: 0,
        type: '派单',
        title: '运营实习生',
        location: '罗湖区',
        viewCount: 233,
        createdAt: '2011-01-01 11:11',
        price: '3000',
        priceType: '月'
      },
      {
        id: 0,
        type: '派单',
        title: '运营实习生',
        location: '罗湖区',
        viewCount: 233,
        createdAt: '2011-01-01 11:11',
        price: '3000',
        priceType: '月'
      },
      {
        id: 0,
        type: '派单',
        title: '运营实习生',
        location: '罗湖区',
        viewCount: 233,
        createdAt: '2011-01-01 11:11',
        price: '3000',
        priceType: '月'
      }
    ]
  },
  mutations: {
    [types.EDIT_PART_TIME_SEARCH_CONDITION] (state, { partTimeSearchCondition }) {
      state.partTimeSearchCondition = partTimeSearchCondition;
    },
    [types.EDIT_PART_TIME_BASE_INFO] (state, { partTimeBaseInfo }) {
      state.partTimeBaseInfo = partTimeBaseInfo;
    }
  },
  actions: {
    async asyncInitPartTimeBaseInfo ({ commit }) {
      const result = await api.partTime.getBaseInfo();
      // 必须为新对象, 需要触发 watch
      let partTimeBaseInfo = {
        partTimeTypeList: [],
        partTimeSpeciesList: [],
        calculationTypeList: [],
        settlementTypeList: []
      };
      if (result.code === 0) {
        partTimeBaseInfo.partTimeTypeList = result.partTimeTypes;
        partTimeBaseInfo.partTimeSpeciesList = result.partTimeSpecies;
        partTimeBaseInfo.calculationTypeList = result.calculationTypes;
        partTimeBaseInfo.settlementTypeList = result.settlementTypes;
      }
      commit(types.EDIT_PART_TIME_BASE_INFO, {
        partTimeBaseInfo: partTimeBaseInfo
      });
    },
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
