import types from '@/store/mutation-types';

export default {
  namespaced: true,
  state: {
    // 路由列表
    routerList: {}
  },
  mutations: {
    [types.EDIT_ROUTER_LIST] (state, { routerList }) {
      state.routerList = routerList;
    }
  },
  actions: {
    editRouterList ({ commit }, { routerList }) {
      // 修改可访问路由表
      commit(types.EDIT_ROUTER_LIST, {
        routerList
      });
    }
  },
  getters: {}
};
