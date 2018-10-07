import Vue from 'vue';
import Vuex from 'vuex';

// 引入 module
import location from '@/store/location';
import account from '@/store/account/index';
import partTime from '@/store/part-time/index';

// 注入 Vuex 插件
Vue.use(Vuex);

// 导出 Vuex Store 对象
export default new Vuex.Store({
  modules: {
    location,
    account,
    partTime
  }
});
