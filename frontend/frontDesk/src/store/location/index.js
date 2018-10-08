import types from '@/store/mutation-types';
import api from '@/api';

export default {
  namespaced: true,
  state: {
    // 所有的区域
    regionList: [],
    // 默认所在城市
    defaultCity: {},
    // 当前所在城市
    currentCity: {}
  },
  mutations: {
    [types.EDIT_REGION_LIST] (state, { regionList }) {
      state.regionList = regionList;
    },
    [types.EDIT_DEFAULT_CITY] (state, { defaultCity }) {
      state.defaultCity = defaultCity;
    },
    [types.EDIT_CURRENT_CITY] (state, { currentCity }) {
      state.currentCity = currentCity;
    }
  },
  actions: {
    async initRegionList ({ commit, state, dispatch }) {
      // 只初始化一次
      if (state.initRegionListFlag !== undefined) return;
      state.initRegionListFlag = false;

      // 当前城市和区域列表
      const result = await api.location.getRegionList();

      if (result.code === 0) {
        // 修改地区列表
        commit(types.EDIT_REGION_LIST, {
          regionList: result.regionList
        });
        // 修改默认所在城市
        commit(types.EDIT_DEFAULT_CITY, {
          defaultCity: result.currentCity
        });
      }
    },
    editCurrentCityByCityId ({ commit, state }, { cityId }) {
      let currentCity = null;
      if (cityId !== 0) {
        state.regionList.find(provinceItem => {
          const cityItem = provinceItem.children.find(cityItem => cityItem.id === parseInt(cityId));
          if (cityItem) {
            currentCity = cityItem;
            return true;
          }
        });
      }
      // 找不到取默认
      if (currentCity === null) {
        currentCity = state.defaultCity;
      }
      commit(types.EDIT_CURRENT_CITY, {
        currentCity
      });
    }
  },
  getters: {}
};
