<template>
  <section class="part-time-search-result-list-container">
    <div class="part-time-result-list">
      <div class="part-time-item"
           v-for="(partTimeItem, index) in partTimeSearchResultList"
           :key="index">
        <div class="part-time-base-info">
          <router-link class="part-time-name" :to="`/partTime/detail/${index}`">
            <p>简单小任务，在家躺赚</p>
          </router-link>
          <div class="part-time-base-info-list">
            <div>
              <span class="part-time-base-info-item">
                <span>兼职类型: </span>
                <span>长期可做</span>
              </span>
              <span class="part-time-base-info-item">
                <span>兼职种类: </span>
                <span>传单派发</span>
              </span>
            </div>
            <div>
              <span class="part-time-base-info-item">
                <span>工作地点: </span>
                <span>罗湖区</span>
              </span>
              <span class="part-time-base-info-item">
                <span>招聘人数: </span>
                <span>500</span>
              </span>
            </div>
          </div>
        </div>
        <div class="part-time-price-info">
          <span class="price-info">
            <span class="price">200</span>
            <span>元 / 天</span>
            <span class="settlement-type">日结</span>
          </span>
        </div>
      </div>
    </div>
    <section class="part-time-search-result-p">
      <el-pagination
        background
        layout="prev, pager, next"
        @current-change="searchPartTime"
        :page-size="pageSize"
        :pager-count="pageCount"
        :current-page="currentPage"
        :total="total">
      </el-pagination>
    </section>
  </section>
</template>

<script>
import { mapState } from 'vuex';
import routerUtils from '@/utils/router';

export default {
  name: 'PartTimeList',
  data() {
    return {
      pageSize: 10,
      pageCount: 11,
      currentPage: 0,
      total: 1000
    };
  },
  computed: {
    ...mapState('partTime', [
      'partTimeSearchCondition',
      'partTimeSearchResultList'
    ])
  },
  watch: {
    partTimeSearchCondition: {
      deep: true,
      handler(partTimeSearchCondition) {
        // init 分页
        if (this.currentPage !== partTimeSearchCondition.limitStart / 10) {
          this.currentPage = partTimeSearchCondition.limitStart / 10;
        }
      }
    }
  },
  methods: {
    async searchPartTime(currentPage) {
      const limitStart = currentPage * this.pageSize;
      const limitSize = this.pageSize;
      // url 更新兼职搜索分页
      this.$router.push({
        path: this.$router.pah,
        query: routerUtils.getNewPartTimeSearchConditionUrlParams(this.$route.query, {
          limitStart,
          limitSize
        })
      });
    }
  }
};
</script>

<style lang="scss">
  .part-time-search-result-list-container {
    box-shadow: 0 0 50px rgba(0, 0, 0, 0.1);
    margin-top: 20px;
    .part-time-result-list {
      box-shadow: 5px 5px 11px rgba(0, 0, 0, 0.1);
      .part-time-item {
        display: flex;
        justify-content: space-between;
        padding: 20px;
        border-bottom: 1px solid #ddd;
        font-size: 12px;
        color: #999;
        .part-time-base-info {
          .part-time-name {
            display: block;
            margin-bottom: 20px;
            font: 700 16px "microsoft yahei";
            color: #333;
          }
          .part-time-name:hover {
            text-decoration: underline;
          }
          .part-time-base-info-list {
            div {
              display: flex;
              margin-bottom: 10px;
              .part-time-base-info-item {
                width: 200px;
                span:last-child {
                  color: #666;
                  margin-left: 5px;
                }
              }
            }
            div:last-child {
              margin-bottom: 0;
            }
          }
        }
        .part-time-price-info {
          display: flex;
          align-items: center;
          .price {
            margin-right: 10px;
            font-weight: bold;
            font-size: 19px;
            color: #f60;
          }
          .settlement-type {
            margin-left: 20px;
          }
        }
      }
    }
    .part-time-search-result-p {
      display: flex;
      justify-content: center;
      padding-top: 30px;
      padding-bottom: 100px;
      .el-pagination.is-background {
        button,
        li {
          width: 40px;
          height: 40px;
          line-height: 40px;
          font-size: 16px;
        }
      }
    }
  }
</style>
