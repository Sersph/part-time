import types from '@/store/mutation-type';
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
    partTimeSearchResult: {
      total: 0,
      pages: 0,
      pageNumber: 1,
      pageSize: 10,
      rows: []
    }
  },
  mutations: {
    [types.EDIT_PART_TIME_SEARCH_CONDITION](state, { partTimeSearchCondition }) {
      state.partTimeSearchCondition = partTimeSearchCondition;
    },
    [types.EDIT_PART_TIME_SEARCH_RESULT](state, { partTimeSearchResult }) {
      state.partTimeSearchResult = partTimeSearchResult;
    },
    [types.EDIT_PART_TIME_BASE_INFO](state, { partTimeBaseInfo }) {
      state.partTimeBaseInfo = partTimeBaseInfo;
    }
  },
  actions: {
    async asyncInitPartTimeBaseInfo({ commit }) {
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
    editPartTimeSearchCondition({ commit }, { partTimeSearchCondition }) {
      commit(types.EDIT_PART_TIME_SEARCH_CONDITION, { partTimeSearchCondition });
    },
    async asyncEditPartTimeSearchResultList({ commit, state }) {
      const searchCondition = {
        cityId: state.partTimeSearchCondition.cityId,
        areaId: state.partTimeSearchCondition.areaId,
        partTimeTypeId: state.partTimeSearchCondition.partTimeTypeId,
        partTimeSpeciesId: state.partTimeSearchCondition.partTimeSpeciesId,
        pageNumber: state.partTimeSearchCondition.pageNumber,
        pageSize: state.partTimeSearchCondition.pageSize
      };
      if (state.partTimeSearchCondition.keyword) {
        // 模糊匹配拼接
        searchCondition.keyword = `%${state.partTimeSearchCondition.keyword}%`;
      }
      const result = await api.partTime.getPartTime(searchCondition);
      commit(types.EDIT_PART_TIME_SEARCH_RESULT, { partTimeSearchResult: result });
    }
  },
  getters: {}
};
