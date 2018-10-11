import Vue from 'vue';
import Vuex from 'vuex';

// 引入 module
import account from '@/store/account';
import permission from '@/store/permission';

// 注入 Vuex 插件
Vue.use(Vuex);

// 导出 Vuex Store 对象
export default new Vuex.Store({
  modules: {
    account,
    permission
  }
});
