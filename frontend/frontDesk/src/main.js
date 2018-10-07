import Vue from 'vue';
import App from '@/App.vue';
import router from '@/router';
import store from '@/store';

// 注入公共过滤器
import '@/common/filters';

// 注入公共组件[element等]
import '@/common/components';

// 关闭生产环境的提示
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
