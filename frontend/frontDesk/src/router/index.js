import Vue from 'vue';
import VueRouter from 'vue-router';

// 非路由懒加载
import SignUp from '@/pages/account/SignUp';
import SignIn from '@/pages/account/SignIn';
import PersonalPartTime from '@/pages/account/personal/part-time/PersonalPartTime';
import PersonalPartTimeApply from '@/pages/account/personal/part-time/PersonalPartTimeApply';
import PersonalPartTimeApplyList from '@/pages/account/personal/part-time/PersonalPartTimeApplyList';
import PersonalSetting from '@/pages/account/personal/setting/PersonalSetting';
import PersonalSettingResume from '@/pages/account/personal/setting/PersonalSettingResume';
import EnterprisePartTime from '@/pages/account/enterprise/part-time/EnterprisePartTime';
import EnterprisePartTimePost from '@/pages/account/enterprise/part-time/EnterprisePartTimePost';
import EnterprisePartTimePostList from '@/pages/account/enterprise/part-time/EnterprisePartTimePostList';
import EnterprisePartTimePostOperation from '@/pages/account/enterprise/part-time/EnterprisePartTimePostOperation';
import EnterprisePartTimeRecruitment from '@/pages/account/enterprise/part-time/EnterprisePartTimeRecruitment';
import EnterprisePartTimeRecruitmentList from '@/pages/account/enterprise/part-time/EnterprisePartTimeRecruitmentList';
import EnterpriseSetting from '@/pages/account/enterprise/setting/EnterpriseSetting';
import EnterpriseSettingCertification from '@/pages/account/enterprise/setting/EnterpriseSettingCertification';
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
          component: () => import('@/pages/account/personal/Personal'),
          redirect: '/account/personal/partTime',
          children: [
            {
              path: '/account/personal/partTime',
              component: PersonalPartTime,
              redirect: '/account/personal/partTime/partTimeApply',
              meta: {
                needSignIn: true,
                index: '1',
                icon: 'people',
                name: '我的兼职'
              },
              children: [
                {
                  path: '/account/personal/partTime/partTimeApply',
                  component: PersonalPartTimeApply,
                  redirect: '/account/personal/partTime/partTimeApply/list',
                  meta: {
                    needSignIn: true,
                    index: '1-1',
                    name: '我的申请'
                  },
                  children: [
                    {
                      path: '/account/personal/partTime/partTimeApply/list',
                      component: PersonalPartTimeApplyList,
                      meta: {
                        needSignIn: true,
                        index: '1-1',
                        name: '申请列表'
                      }
                    }
                  ]
                }
              ]
            },
            {
              path: '/account/personal/setting',
              component: PersonalSetting,
              redirect: '/account/personal/setting/resume',
              meta: {
                needSignIn: true,
                index: '2',
                icon: 'view_list',
                name: '设置'
              },
              children: [
                {
                  path: '/account/personal/setting/resume',
                  component: PersonalSettingResume,
                  meta: {
                    needSignIn: true,
                    index: '2-1',
                    name: '我的简历'
                  }
                }
              ]
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
              component: EnterprisePartTime,
              redirect: '/account/enterprise/partTime/partTimePost',
              meta: {
                needSignIn: true,
                index: '1',
                icon: 'people',
                name: '我的兼职'
              },
              children: [
                {
                  path: '/account/enterprise/partTime/partTimePost',
                  component: EnterprisePartTimePost,
                  redirect: '/account/enterprise/partTime/partTimePost/list',
                  meta: {
                    needSignIn: true,
                    index: '1-1',
                    name: '我的发布'
                  },
                  children: [
                    {
                      path: '/account/enterprise/partTime/partTimePost/list',
                      component: EnterprisePartTimePostList,
                      meta: {
                        needSignIn: true,
                        index: '1-1-1',
                        name: '兼职列表'
                      }
                    },
                    {
                      path: '/account/enterprise/partTime/partTimePost/add',
                      component: EnterprisePartTimePostOperation,
                      meta: {
                        needSignIn: true,
                        index: '1-1-2',
                        name: '发布兼职'
                      }
                    }
                  ]
                },
                {
                  path: '/account/enterprise/partTime/partTimeRecruitment',
                  component: EnterprisePartTimeRecruitment,
                  redirect: '/account/enterprise/partTime/partTimeRecruitment/list',
                  meta: {
                    needSignIn: true,
                    index: '1-2',
                    name: '我的招聘'
                  },
                  children: [
                    {
                      path: '/account/enterprise/partTime/partTimeRecruitment/list',
                      component: EnterprisePartTimeRecruitmentList,
                      meta: {
                        needSignIn: true,
                        index: '1-1-1',
                        name: '招聘列表'
                      }
                    }
                  ]
                }
              ]
            },
            {
              path: '/account/enterprise/setting',
              component: EnterpriseSetting,
              redirect: '/account/enterprise/partTime/setting/certification',
              meta: {
                needSignIn: true,
                index: '2',
                icon: 'view_list',
                name: '设置'
              },
              children: [
                {
                  path: '/account/enterprise/setting/certification',
                  component: EnterpriseSettingCertification,
                  meta: {
                    needSignIn: true,
                    index: '2-2',
                    name: '企业认证'
                  }
                }
              ]
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
