<template>
  <section class="home-container">
    <section class="part-time-container" :span="20">
      <part-time-top/>
      <part-time-search-condition/>
      <part-time-search-result-list/>
    </section>
    <footer-nav/>
  </section>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import NProgress from 'nprogress';
import routerUtils from '@/utils/router';
import PartTimeTop from '@/components/part-time/PartTimeTop';
import PartTimeSearchCondition from '@/components/part-time/PartTimeSearchCondition';
import PartTimeSearchResultList from '@/components/part-time/PartTimeSearchResultList';
import FooterNav from '@/components/footer/FooterNav';

export default {
  name: 'Home',
  components: {
    PartTimeTop,
    PartTimeSearchCondition,
    PartTimeSearchResultList,
    FooterNav,
    flag: false
  },
  computed: {
    ...mapState('location', [
      'currentCity'
    ])
  },
  async mounted () {
    // 初始化地区
    await this.initRegionList();
    // 初始化搜索结果
    await this.asyncEditPartTimeSearchResultListByUrlParams();
  },
  watch: {
    // 监听 url 搜索兼职
    $route: {
      async handler () {
        if (this.$route.path === '/home') {
          await this.asyncEditPartTimeSearchResultListByUrlParams();
        }
      }
    }
  },
  methods: {
    ...mapActions('location', [
      'editCurrentCityByCityId',
      'initRegionList'
    ]),
    ...mapActions('partTime', [
      'editPartTimeSearchCondition',
      'asyncEditPartTimeSearchResultList'
    ]),
    async asyncEditPartTimeSearchResultListByUrlParams () {
      // 初始化所在城市
      this.editCurrentCityByCityId({
        cityId: this.$route.query.cityId !== undefined ? this.$route.query.cityId : '0'
      });

      // 初始化搜索条件
      const partTimeSearchConditionURLParams = this.$route.query;
      this.editPartTimeSearchCondition({
        partTimeSearchCondition: routerUtils.getNewPartTimeSearchConditionUrlParams({
          cityId: '0',
          areaId: '0',
          typeId: '0',
          speciesId: '0',
          keyword: '',
          limitStart: '0',
          endStart: '0'
        }, partTimeSearchConditionURLParams)
      });

      // 开始搜索兼职
      NProgress.start();
      await this.asyncEditPartTimeSearchResultList();
      NProgress.done();
    }
  }
};
</script>

<style lang="scss">
  .home-container {
    width: 100% !important;
    .part-time-container {
      width: 83.33333%;
      margin-left: auto;
      margin-right: auto;
    }
  }

  // 模态框定位问题
  .el-loading-mask {
    top: 60px;
  }
</style>
