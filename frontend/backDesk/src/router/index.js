import Vue from 'vue';
import VueRouter from 'vue-router';

// 非懒加载路由组件
import Master from '@/pages/master/Master';

// 注入 Vue Router 插件
Vue.use(VueRouter);

// 导出 Vue Router 对象
export default new VueRouter({
  base: '/admin/',
  mode: 'history',
  linkActiveClass: 'active',
  routes: [
    {
      path: '/',
      component: () => import('@/pages/dashboard/Dashboard'),
      redirect: '/dashboard/home',
      meta: {
        isMasterPage: false
      }
    },
    // 非大后台页面
    {
      path: '/account',
      component: () => import('@/pages/account/Account'),
      meta: {
        isMasterPage: false
      },
      children: [
        {
          path: '/account/signIn',
          component: () => import('@/pages/account/SignIn')
        }
      ]
    },
    // 大后台页面
    {
      path: '/dashboard/',
      component: Master,
      meta: {
        needSignIn: true,
        isMasterPage: true,
        index: '1',
        icon: 'dashboard',
        name: '仪表盘'
      },
      children: [
        {
          path: '/dashboard/home',
          component: () => import('@/pages/dashboard/DashboardHome'),
          meta: {
            needSignIn: true,
            index: '1-1',
            name: '首页'
          }
        }
      ]
    },
    {
      path: '/enterprise',
      component: Master,
      meta: {
        needSignIn: true,
        isMasterPage: true,
        index: '2',
        icon: 'people',
        name: '企业'
      },
      children: [
        {
          path: '/enterprise/certification',
          component: () => import('@/pages/enterprise/EnterpriseCertification'),
          redirect: '/enterprise/certification/list',
          meta: {
            needSignIn: true,
            index: '2-1',
            name: '企业认证'
          },
          children: [
            {
              path: '/enterprise/certification/list',
              component: () => import('@/pages/enterprise/EnterpriseCertificationList'),
              meta: {
                needSignIn: true,
                index: '2-1-1',
                name: '列表'
              }
            },
            {
              path: '/enterprise/certification/add',
              component: () => import('@/pages/enterprise/EnterpriseCertificationOperation'),
              meta: {
                needSignIn: true,
                index: '2-1-2',
                name: '添加'
              }
            }
          ]
        }
      ]
    }
  ]
});
