import types from '@/store/mutation-types';
import api from '@/api';

export default {
  namespaced: true,
  state: {
    // 用户信息
    userInfo: {}
  },
  mutations: {
    [types.EDIT_USER_INFO] (state, { userInfo }) {
      state.userInfo = userInfo;
    }
  },
  actions: {
    async asyncInitUserInfo ({ commit }) {
      const result = await api.account.getUserInfo();
      let userInfo = {};
      if (result.code === 0) {
        userInfo = result.userInfo;
      }
      commit(types.EDIT_USER_INFO, {
        userInfo
      });
    }
  },
  getters: {}
};
