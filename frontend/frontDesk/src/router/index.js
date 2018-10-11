import Vue from 'vue';
import VueRouter from 'vue-router';

// 非懒加载路由组件
import SignIn from '@/pages/account/SignIn';
import SignUp from '@/pages/account/SignUp';
import PersonalPartTimeApply from '@/pages/account/PersonalPartTimeApply';
import PersonalResume from '@/pages/account/PersonalResume';
import EnterprisePartTimePost from '@/pages/account/EnterprisePartTimePost';
import EnterprisePartTimeRecruitment from '@/pages/account/EnterprisePartTimeRecruitment';
import EnterpriseCertification from '@/pages/account/EnterpriseCertification';
import PartTimeDetail from '@/pages/part-time/PartTimeDetail';

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
          component: SignUp
        },
        {
          path: '/account/signIn',
          component: SignIn
        },
        {
          path: '/account/personal',
          component: () => import('@/pages/account/Personal'),
          redirect: '/account/personal/partTimeApply',
          children: [
            {
              path: '/account/personal/partTimeApply',
              component: PersonalPartTimeApply,
              meta: {
                needSignIn: true
              }
            },
            {
              path: '/account/personal/resume',
              component: PersonalResume,
              meta: {
                needSignIn: true
              }
            }
          ]
        },
        {
          path: '/account/enterprise',
          component: () => import('@/pages/account/Enterprise'),
          redirect: '/account/enterprise/partTimePost',
          children: [
            {
              path: '/account/enterprise/partTimePost',
              component: EnterprisePartTimePost,
              meta: {
                needSignIn: true
              }
            },
            {
              path: '/account/enterprise/partTimeRecruitment',
              component: EnterprisePartTimeRecruitment,
              meta: {
                needSignIn: true
              }
            },
            {
              path: '/account/enterprise/certification',
              component: EnterpriseCertification,
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
          component: PartTimeDetail
        }
      ]
    }
  ]
});
