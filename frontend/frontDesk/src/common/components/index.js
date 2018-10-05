import Vue from 'vue';
// 初始化 css
import 'normalize.css';
// material css
import 'material-icons/iconfont/material-icons.scss';
// nprogress
import NProgress from 'nprogress';
import 'nprogress/nprogress.css';
// element css
import 'element-ui/lib/theme-chalk/index.css';

// element component
// import { Menu, MenuItem, Submenu } from 'element-ui';
// Vue.use(Menu);
// Vue.use(MenuItem);
// Vue.use(Submenu);
import ElementUI from 'element-ui';

// 注入 element component
Vue.use(ElementUI);

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
