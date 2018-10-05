import Vue from 'vue';
import VueRouter from 'vue-router';

// 非懒加载路由组件
import SignIn from '@/pages/account/SignIn';
import SignUp from '@/pages/account/SignUp';
import ProfileApply from '@/pages/account/ProfileApply';

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
        showFooterNav: true,
        showSelectCity: true
      }
    },
    {
      path: '/account',
      component: () => import('@/pages/account/Account'),
      children: [
        {
          path: '/account/signUp',
          component: SignUp,
          meta: {
            showFooterNav: true
          }
        },
        {
          path: '/account/signIn',
          component: SignIn,
          meta: {
            showFooterNav: true
          }
        },
        {
          path: '/account/profile',
          component: () => import('@/pages/account/Profile'),
          children: [
            {
              path: '/account/profile/apply',
              component: ProfileApply
            }
          ]
        }
      ]
    }
  ]
});
