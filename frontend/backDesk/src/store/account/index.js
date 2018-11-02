import types from '@/store/mutation-type';
import api from '@/api';

export default {
  namespaced: true,
  state: {
    // 用户信息
    accountInfo: {}
  },
  mutations: {
    [types.EDIT_ACCOUNT_INFO](state, { accountInfo }) {
      state.accountInfo = accountInfo;
    }
  },
  actions: {
    async asyncInitAccountInfo({ commit }) {
      const result = await api.account.getAccountInfo();
      // 必须为新对象, 需要触发 watch
      let accountInfo = {};
      if (result.code === 0) {
        accountInfo = result.accountInfo;
      }
      commit(types.EDIT_ACCOUNT_INFO, {
        accountInfo
      });
    }
  },
  getters: {}
};
