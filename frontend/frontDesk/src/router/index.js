import Vue from 'vue';
import VueRouter from 'vue-router';

// 非懒加载路由组件
import SignIn from '@/pages/account/SignIn';
import SignUp from '@/pages/account/SignUp';
import ProfilePartTimeApply from '@/pages/account/ProfilePartTimeApply';
import ProfileResume from '@/pages/account/ProfileResume';
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
          path: '/account/profile',
          component: () => import('@/pages/account/Profile'),
          children: [
            {
              path: '/account/profile',
              redirect: '/account/profile/partTimeApply'
            },
            {
              path: '/account/profile/partTimeApply',
              component: ProfilePartTimeApply
            },
            {
              path: '/account/profile/resume',
              component: ProfileResume
            }
          ]
        },
        {
          path: '/account/enterprise',
          component: () => import('@/pages/account/Enterprise'),
          children: [
            {
              path: '/account/enterprise',
              redirect: '/account/enterprise/partTimePost'
            },
            {
              path: '/account/enterprise/partTimePost',
              component: EnterprisePartTimePost
            },
            {
              path: '/account/enterprise/partTimeRecruitment',
              component: EnterprisePartTimeRecruitment
            },
            {
              path: '/account/enterprise/certification',
              component: EnterpriseCertification
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
