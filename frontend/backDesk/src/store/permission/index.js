import types from '@/store/mutation-types';

export default {
  namespaced: true,
  state: {
    // 路由列表
    routeList: {}
  },
  mutations: {
    [types.EDIT_ROUTE_LIST] (state, { routeList }) {
      state.routeList = routeList;
    }
  },
  actions: {
    editRouteList ({ commit }, { routeList }) {
      // 修改可访问路由表
      commit(types.EDIT_ROUTE_LIST, {
        routeList
      });
    }
  },
  getters: {}
};
