<template>
  <section class="part-time-search-result-list-container">
    <section class="part-time-search-result-list-wrapper"
             :class="{loading: loadingFlag}">
      <section class="part-time-result-list"
               v-if="partTimeSearchResult.rows && partTimeSearchResult.rows.length > 0">
        <div class="part-time-item"
             v-for="(partTimeItem, index) in partTimeSearchResult.rows"
             :key="index">
          <div class="part-time-base-info">
            <router-link class="part-time-name" :to="`/partTime/detail/${partTimeItem.id}`">
              <p>{{ partTimeItem.name }}</p>
            </router-link>
            <div class="part-time-base-info-list">
              <div>
                  <span class="part-time-base-info-item">
                    <span>兼职类型: </span>
                    <span>{{ partTimeItem.partTimeType }}</span>
                  </span>
                <span class="part-time-base-info-item">
                    <span>兼职种类: </span>
                    <span>{{ partTimeItem.partTimeSpecies }}</span>
                  </span>
              </div>
              <div>
                  <span class="part-time-base-info-item">
                    <span>工作地点: </span>
                    <span>{{ partTimeItem.area }}</span>
                  </span>
                <span class="part-time-base-info-item">
                    <span>招聘人数: </span>
                    <span>{{ partTimeItem.recruitmentCount }}</span>
                  </span>
              </div>
            </div>
          </div>
          <div class="part-time-price-info">
              <span class="price-info">
                <span class="price">{{ partTimeItem.price }}</span>
                <span>元 / {{ partTimeItem.calculationType }}</span>
                <span class="settlement-type">{{ partTimeItem.settlementType }}</span>
              </span>
          </div>
        </div>
      </section>
      <section class="not-row"
               v-if="partTimeSearchResult.rows && partTimeSearchResult.rows.length === 0">
        <p class="img"></p>
        <p>暂无符合条件职位</p>
      </section>
      <section class="part-time-search-result-p"
               v-if="partTimeSearchResult.rows && partTimeSearchResult.rows.length > 0">
        <el-pagination
          :current-page="partTimeSearchResult.pageNumber"
          :page-size="partTimeSearchResult.size"
          :page-sizes="[10, 20, 50]"
          :total="partTimeSearchResult.total"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handlePageSize"
          @current-change="handlePageNumber"
        >
        </el-pagination>
      </section>
    </section>
  </section>
</template>

<script>
import { mapState } from 'vuex';
import routerUtils from '@/utils/router';
import paginationHandler from '@/mixin/paginationHandler';

export default {
  name: 'PartTimeList',
  mixins: [
    paginationHandler
  ],
  data() {
    return {
      loadingFlag: false
    };
  },
  computed: {
    ...mapState('partTime', [
      'partTimeSearchCondition',
      'partTimeSearchResult'
    ])
  },
  watch: {
    partTimeSearchCondition: {
      handler() {
        this.loadingFlag = true;
      }
    },
    partTimeSearchResult: {
      deep: true,
      handler() {
        // 回到顶部
        document.documentElement.scrollTop = 0;
        this.$nextTick(() => {
          setTimeout(() => {
            this.loadingFlag = false;
          }, 200);
        });
      }
    }
  },
  methods: {
    async searchData() {
      // url 更新兼职搜索分页
      this.$router.push({
        path: this.$router.path,
        query: routerUtils.getNewPartTimeSearchConditionUrlParams(this.$route.query, {
          pageNumber: this.pageNumber,
          pageSize: this.pageSize
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
    margin-bottom: 20px;
    .part-time-search-result-list-wrapper {
      transition: opacity .1s;
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
      .not-row {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 215px;
        .img {
          width: 60px;
          height: 60px;
          background-image: url(../../assets/warn.png);
        }
        p:last-child {
          font-size: 15px;
          color: #999;
          margin-top: 20px;
        }
      }
      .part-time-search-result-p {
        display: flex;
        justify-content: center;
        padding-top: 50px;
        padding-bottom: 55px;
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
    .loading {
      opacity: .5;
    }
  }
</style>
