import types from '@/store/mutation-types';

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
    initRegionList ({ commit, state, dispatch }) {
      // 只初始化一次
      if (state.initRegionListFlag !== undefined) return;
      state.initRegionListFlag = false;

      const regionList = [
        {
          id: '1',
          name: '广东省',
          parentId: '0',
          children: [
            {
              id: '2',
              name: '深圳市',
              parentId: '1',
              children: [
                { id: '4', name: '罗湖区', parentId: '2' },
                { id: '5', name: '福田区', parentId: '2' }
              ]
            },
            {
              id: '3',
              name: '广州市',
              parentId: '1',
              children: [
                { id: '6', name: '越秀区', parentId: '3' },
                { id: '7', name: '天河区', parentId: '3' }]
            }
          ]
        },
        {
          id: '8',
          name: '浙江省',
          parentId: '0',
          children: [
            {
              id: '9',
              name: '杭州市',
              parentId: '8',
              children: [
                { id: '10', name: '萧山区', parentId: '9' },
                { id: '11', name: '富阳区', parentId: '9' }
              ]
            }
          ]
        }
      ];
      const defaultCity = {
        id: '9',
        name: '杭州市'
      };

      console.log(JSON.stringify(regionList));
      // 修改地区列表
      commit(types.EDIT_REGION_LIST, {
        regionList
      });
      // 修改默认所在城市
      commit(types.EDIT_DEFAULT_CITY, {
        defaultCity
      });
    },
    editCurrentCityByCityId ({ commit, state }, { cityId }) {
      let currentCity = null;
      if (cityId !== 0) {
        state.regionList.find(provinceItem => {
          const cityItem = provinceItem.children.find(cityItem => cityItem.id === cityId);
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
