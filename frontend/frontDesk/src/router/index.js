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
      redirect: '/home'
    },
    {
      path: '/home',
      component: () => import('@/pages/home/Home'),
      meta: {
        showSelectCity: true
      }
    },
    {
      path: '/account',
      component: () => import('@/pages/account/Account'),
      children: [
        {
          path: '/account/signUp',
          component: () => import('@/pages/account/SignUp')
        },
        {
          path: '/account/signIn',
          component: () => import('@/pages/account/SignIn')
        },
        {
          path: '/account/personal',
          component: () => import('@/pages/account/personal/Personal'),
          redirect: '/account/personal/partTimeApply',
          children: [
            {
              path: '/account/personal/partTimeApply',
              component: () => import('@/pages/account/personal/PersonalPartTimeApply'),
              redirect: '/account/personal/partTimeApply/list',
              meta: {
                needSignIn: true
              },
              children: [
                {
                  path: '/account/personal/partTimeApply/list',
                  component: () => import('@/pages/account/personal/PersonalPartTimeApplyList'),
                  meta: {
                    needSignIn: true
                  }
                }
              ]
            },
            {
              path: '/account/personal/resume',
              component: () => import('@/pages/account/personal/PersonalResume'),
              meta: {
                needSignIn: true
              }
            }
          ]
        },
        {
          path: '/account/enterprise',
          component: () => import('@/pages/account/enterprise/Enterprise'),
          redirect: '/account/enterprise/partTime',
          children: [
            {
              path: '/account/enterprise/partTime',
              component: () => import('@/pages/account/enterprise/EnterprisePartTime'),
              redirect: '/account/enterprise/partTime/list',
              meta: {
                needSignIn: true
              },
              children: [
                {
                  path: '/account/enterprise/partTime/list',
                  component: () => import('@/pages/account/enterprise/EnterprisePartTimeList'),
                  meta: {
                    needSignIn: true
                  }
                },
                {
                  path: '/account/enterprise/partTime/add',
                  component: () => import('@/pages/account/enterprise/EnterprisePartTimeOperation'),
                  meta: {
                    needSignIn: true
                  }
                }
              ]
            },
            {
              path: '/account/enterprise/partTimeRecruitment',
              component: () => import('@/pages/account/enterprise/EnterprisePartTimeRecruitment'),
              meta: {
                needSignIn: true
              }
            },
            {
              path: '/account/enterprise/certification',
              component: () => import('@/pages/account/enterprise/EnterpriseCertification'),
              meta: {
                needSignIn: true
              }
            }
          ]
        }
      ]
    },
    {
      path: '/partTime',
      component: () => import('@/pages/part-time/PartTime'),
      children: [
        {
          path: '/partTime/detail/:id',
          component: () => import('@/pages/part-time/PartTimeDetail')
        }
      ]
    }
  ]
});
