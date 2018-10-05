import types from '@/store/mutation-types';

export default {
  namespaced: true,
  state: {
    // 所有的区域
    regionList: [
      { id: '1', name: '广东省', parentId: '0' },
      { id: '2', name: '深圳市', parentId: '1' },
      { id: '3', name: '广州市', parentId: '1' },
      { id: '4', name: '罗湖区', parentId: '2' },
      { id: '5', name: '福田区', parentId: '2' },
      { id: '6', name: '越秀区', parentId: '3' },
      { id: '7', name: '天河区', parentId: '3' },
      { id: '8', name: '浙江省', parentId: '0' },
      { id: '9', name: '杭州市', parentId: '8' },
      { id: '10', name: '萧山区', parentId: '9' },
      { id: '11', name: '富阳区', parentId: '9' }
    ],
    // 所有的省份
    provinceList: [],
    // 所有的城市
    cityList: [],
    // 所有的地区
    areaList: [],
    // 默认所在城市
    defaultCity: {},
    // 当前所在城市
    currentCity: {}
  },
  mutations: {
    [types.EDIT_DEFAULT_CITY] (state, { defaultCity }) {
      state.defaultCity = defaultCity;
    },
    [types.EDIT_CURRENT_CITY] (state, { currentCity }) {
      state.currentCity = currentCity;
    },
    [types.EDIT_PROVINCE_LIST] (state, { provinceList }) {
      state.provinceList = provinceList;
    },
    [types.EDIT_CITY_LIST] (state, { cityList }) {
      state.cityList = cityList;
    },
    [types.EDIT_AREA_LIST] (state, { areaList }) {
      state.areaList = areaList;
    }
  },
  actions: {
    initRegionList ({ commit, state, dispatch }) {
      const defaultCity = {
        id: '9',
        name: '杭州市'
      };
      // 默认所在城市
      commit(types.EDIT_DEFAULT_CITY, {
        defaultCity
      });
      // 当前所在城市
      dispatch('editCurrentCityByCityId', {
        cityId: defaultCity.id
      });
      // 初始化所有省份列表
      commit(types.EDIT_PROVINCE_LIST, {
        provinceList: state.regionList.filter(item => {
          return item.parentId === '0';
        })
      });
      // 初始化所有城市列表
      commit(types.EDIT_CITY_LIST, {
        cityList: state.regionList.filter(item => {
          return state.provinceList.findIndex(item2 => item.parentId === item2.id) > -1;
        })
      });
      // 初始化所有地区列表
      commit(types.EDIT_AREA_LIST, {
        areaList: state.regionList.filter(item => {
          return state.cityList.findIndex(item2 => item.parentId === item2.id) > -1;
        })
      });
    },
    editCurrentCityByCityId ({ commit, state }, { cityId }) {
      let currentCity = {};
      if (cityId === '0') {
        // 为 0 取默认
        currentCity = state.defaultCity;
      } else {
        // 不为 0 遍历查找城市
        currentCity = state.regionList.find(item => item.id === cityId);
      }
      commit(types.EDIT_CURRENT_CITY, {
        currentCity
      });
    }
  },
  getters: {}
};
