import Vue from 'vue';
import VueRouter from 'vue-router';

// 非懒加载路由组件
import SignIn from '@/pages/account/SignIn';

// 注入 Vue Router 插件
Vue.use(VueRouter);

// 导出 Vue Router 对象
export default new VueRouter({
  mode: 'history',
  linkActiveClass: 'active',
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/home',
      component: () => import('@/pages/home/Home'),
      meta: {
        index: 1
      }
    },
    {
      path: '/account',
      component: () => import('@/pages/account/Account'),
      children: [
        {
          path: '/account/signIn',
          component: SignIn,
          meta: {
            index: 2
          }
        }
      ]
    }
  ]
});
