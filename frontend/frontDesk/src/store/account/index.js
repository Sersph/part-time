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
      if (result.code === 0) {
        commit(types.EDIT_USER_INFO, {
          userInfo: result.userInfo
        });
      }
    }
  },
  getters: {}
};
