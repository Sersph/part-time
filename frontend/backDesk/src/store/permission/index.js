import types from '@/store/mutation-types';

export default {
  namespaced: true,
  state: {
    // 菜单
    sidebarMenuList: {}
  },
  mutations: {
    [types.EDIT_SIDEBAR_MENU_LIST] (state, { sidebarMenuList }) {
      state.sidebarMenuList = sidebarMenuList;
    }
  },
  actions: {
    editSidebarMenuList ({ commit }, { sidebarMenuList }) {
      // 修改可访问路由表
      commit(types.EDIT_SIDEBAR_MENU_LIST, {
        sidebarMenuList
      });
    }
  },
  getters: {}
};
