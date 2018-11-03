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
import {
  Menu,
  MenuItem,
  Submenu,
  Dialog,
  Button,
  Row,
  Col,
  Input,
  RadioGroup,
  Pagination,
  RadioButton,
  Breadcrumb,
  BreadcrumbItem,
  Form,
  FormItem,
  Select,
  Option,
  Cascader,
  DatePicker,
  TimePicker,
  CheckboxGroup,
  CheckboxButton,
  Table,
  TableColumn,
  Tabs,
  TabPane,
  Notification
} from 'element-ui';

// 注入 element component
Vue.use(Menu);
Vue.use(MenuItem);
Vue.use(Submenu);
Vue.use(Dialog);
Vue.use(Button);
Vue.use(Row);
Vue.use(Col);
Vue.use(Input);
Vue.use(RadioGroup);
Vue.use(Pagination);
Vue.use(RadioButton);
Vue.use(Breadcrumb);
Vue.use(BreadcrumbItem);
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Select);
Vue.use(Option);
Vue.use(Cascader);
Vue.use(DatePicker);
Vue.use(TimePicker);
Vue.use(CheckboxGroup);
Vue.use(CheckboxButton);
Vue.use(Table);
Vue.use(TableColumn);
Vue.use(Tabs);
Vue.use(TabPane);
Vue.prototype.$notify = Notification;

// import ElementUI from 'element-ui';
// Vue.use(ElementUI);

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
