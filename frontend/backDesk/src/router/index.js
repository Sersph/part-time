import Vue from 'vue';
import VueRouter from 'vue-router';

// 注入 Vue Router 插件
Vue.use(VueRouter);

// 导出 Vue Router 对象
export default new VueRouter({
  mode: 'history',
  linkActiveClass: 'active',
  routes: [
    {
      path: '/',
      component: () => import('@/pages/dashboard/Dashboard'),
      redirect: '/dashboard/home',
      meta: {
        index: '1',
        icon: 'dashboard',
        name: '仪表盘'
      },
      children: [
        {
          path: '/dashboard/home',
          component: () => import('@/pages/dashboard/DashboardHome'),
          meta: {
            index: '1-1',
            name: '首页'
          }
        }
      ]
    },
    // {
    //   path: '/account'
    // },
    {
      path: '/enterprise',
      component: () => import('@/pages/enterprise/Enterprise'),
      meta: {
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
            index: '2-1',
            name: '企业认证'
          },
          children: [
            {
              path: '/enterprise/certification/list',
              component: () => import('@/pages/enterprise/EnterpriseCertificationList'),
              meta: {
                index: '2-1-1',
                name: '列表'
              }
            },
            {
              path: '/enterprise/certification/add',
              component: () => import('@/pages/enterprise/EnterpriseCertificationOperation'),
              meta: {
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
