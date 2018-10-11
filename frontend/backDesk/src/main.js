import Vue from 'vue';
import App from '@/App.vue';
import router from '@/router';
import store from '@/store';
// nprogrss 进度条
import NProgress from 'nprogress';
import 'nprogress/nprogress.css';
// 初始化 css
import 'normalize.css';
// material 图标库
import 'material-icons/iconfont/material-icons.scss';
// element
import 'element-ui/lib/theme-chalk/index.css';
// import { Menu, MenuItem, Submenu } from 'element-ui';
import ElementUI from 'element-ui';

// Vue.use(Menu);
// Vue.use(MenuItem);
// Vue.use(Submenu);

// 注入 element component
Vue.use(ElementUI);

// 关闭生产环境的提示
Vue.config.productionTip = false;

// NProgress 配置
NProgress.configure({
  minimum: 0.1,
  easing: 'linear',
  positionUsing: '',
  speed: 300,
  trickle: true,
  trickleSpeed: 215,
  trickleRate: 0.25,
  showSpinner: false
});

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
