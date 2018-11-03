<template>
  <section class="part-time-detail-container">
    <el-breadcrumb class="breadcrumb" separator="/">
      <el-breadcrumb-item>{{ partTimeDetail.city }}兼职</el-breadcrumb-item>
      <el-breadcrumb-item>{{ partTimeDetail.partTimeSpecies }}</el-breadcrumb-item>
      <el-breadcrumb-item>{{ partTimeDetail.name }}</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row>
      <el-col :span="16">
        <section class="part-time-detail-left">
          <section class="part-time-base-info">
            <p class="title">
              <span>{{ partTimeDetail.name }}</span>
            </p>
            <p class="price-info">
              <span class="price">
                <span>{{ partTimeDetail.price }}</span>
                <span>元/{{ partTimeDetail.calculationType }}</span>
              </span>
              <span class="type">
                <span>{{ partTimeDetail.settlementType }}</span>
                <span>{{ partTimeDetail.partTimeSpecies }}</span>
              </span>
            </p>
            <p class="apply">
              <el-button type="danger" class="apply" @click="toPartTimeApply">报名参加</el-button>
            </p>
          </section>
          <section class="part-time-detail-info">
            <p class="title">职位详情</p>
            <ul class="part-time-detail-info-list">
              <li>
                <span>招聘人数：</span>
                <span>{{ partTimeDetail.recruitmentCount }}</span>
              </li>
              <li>
                <span>兼职类型：</span>
                <span>{{ partTimeDetail.partTimeType }}</span>
              </li>
              <li>
                <span>兼职种类：</span>
                <span>{{ partTimeDetail.partTimeSpecies }}</span>
              </li>
            </ul>
            <p class="title">薪资待遇</p>
            <ul class="part-time-detail-info-list">
              <li>
                <span>基本工资：</span>
                <span>{{ partTimeDetail.price }}元/{{ partTimeDetail.calculationType }}</span>
              </li>
              <li>
                <span>结算方式：</span>
                <span>{{ partTimeDetail.settlementType }}</span>
              </li>
              <li>
                <span>薪资描述：</span>
                <span>{{ partTimeDetail.priceDescription ? partTimeDetail.priceDescription : '无' }}</span>
              </li>
            </ul>
            <p class="title">工作详情</p>
            <ul class="part-time-detail-info-list">
              <li>
                <span>工作地址：</span>
                <span>{{ partTimeDetail.city }} - {{ partTimeDetail.area }} - {{ partTimeDetail.address }}</span>
              </li>
              <li>
                <span>工作日期：</span>
                <span>{{ partTimeDetail.workingDateStart }} 至 {{ partTimeDetail.workingDateEnd }}</span>
              </li>
              <li>
                <span>上班时间：</span>
                <span>{{ partTimeDetail.workingTime }}</span>
              </li>
              <li>
                <span>工作时间：</span>
                <span>{{ partTimeDetail.workingTimeWeek }}</span>
              </li>
              <li>
                <span>工作描述：</span>
                <span>
                  <div class="part-time-detail-job-info">
                    {{ partTimeDetail.jobDescription }}
                  </div>
                </span>
              </li>
            </ul>
          </section>
        </section>
      </el-col>
      <el-col :span="8">
        <section class="part-time-detail-right">
          <section class="enterprise">
            <div class="enterprise-img">
              <img src="https://sta.doumistatic.com/src/image/company_default.png">
            </div>
            <p class="enterprise-name">{{ partTimeDetail.enterpriseName }}</p>
            <ul class="enterprise-info-list">
              <li>
                <span>公司地址：</span>
                <span>{{ partTimeDetail.enterpriseSite }}</span>
              </li>
            </ul>
          </section>
          <section class="recommend-part-time-list">
            <h5>推荐职位</h5>
            <ul>
              <li>
                <a href="javascript:void(0)">
                  <p class="part-time-name">长期 摄影助理</p>
                  <p class="part-time-city">
                    <span>福田</span>
                    <span>月结</span>
                  </p>
                  <p class="part-time-price">
                    <span>20</span>
                    <span>元/天</span>
                  </p>
                </a>
              </li>
              <li>
                <a href="javascript:void(0)">
                  <p class="part-time-name">长期 摄影助理</p>
                  <p class="part-time-city">
                    <span>福田</span>
                    <span>月结</span>
                  </p>
                  <p class="part-time-price">
                    <span>20</span>
                    <span>元/天</span>
                  </p>
                </a>
              </li>
            </ul>
          </section>
        </section>
      </el-col>
    </el-row>
    <part-time-apply ref="partTimeApply" :partTimeDetail="partTimeDetail"/>
  </section>
</template>

<script>
import NProgress from 'nprogress';
import api from '@/api';
import PartTimeApply from '@/components/part-time/PartTimeApply';

export default {
  name: 'PartTimeDetail',
  data() {
    return {
      partTimeDetail: {}
    };
  },
  components: {
    PartTimeApply
  },
  async beforeCreate() {
    document.documentElement.scrollTop = 0;
    // 获取兼职详情
    const { id } = this.$route.params;
    NProgress.start();
    const result = await api.partTime.getPartTimeDetail(id);
    NProgress.done();
    // 处理数据格式
    // 处理工作时间
    const weekList = ['', '周一', '周二', '周三', '周四', '周五', '周六', '周日'];
    // 1,3,2
    // 1.排序[1,2,3] 2.获取对应值[周一,周二,周三] 3.拼接字符串[周一、周二、周三]
    result.workingTimeWeek = result.workingTimeWeek.split(',').sort().map(week => {
      return `${weekList[week]}`;
    }).join('、');
    // 处理上班时间
    result.workingTime = result.workingTime.split('-').join(' - ');
    this.partTimeDetail = result;
  },
  methods: {
    toPartTimeApply() {
      this.$refs['partTimeApply'].partTimeApplyContainerVisibleFlag = true;
    }
  }
};
</script>

<style lang="scss">
  .part-time-detail-container {
    max-width: 1100px !important;
    min-width: 1000px!important;
    color: #666;
    p {
      margin: 0;
    }
    .breadcrumb {
      padding-top: 20px;
      padding-bottom: 20px;
    }
    .part-time-detail-left {
      padding: 20px;
      box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
      .part-time-base-info {
        .title {
          display: flex;
          align-items: center;
          justify-content: space-between;
          margin-bottom: 20px;
          font-size: 29px;
          color: #3b3b3b;
        }
        .apply {
          font-size: 15px;
        }
        .price-info {
          display: flex;
          align-items: center;
          margin-bottom: 20px;
          .price {
            font-size: 20px;
            font-weight: bold;
            color: #f56c6c;
          }
          .type {
            span {
              position: relative;
              margin-left: 29px;
            }
            span:first-child::after {
              content: '|';
              overflow: hidden;
              position: absolute;
              top: 50%;
              height: 15px;
              margin-left: 13px;
              transform: translateY(-50%);
            }
          }
        }
      }
      .part-time-detail-info {
        margin-top: 35px;
        .title {
          display: flex;
          align-items: center;
          height: 20px;
          margin-bottom: 20px;
          padding-left: 10px;
          font-size: 19px;
          font-weight: bold;
          border-left: 5px solid #409EFF;
        }
        .part-time-detail-info-list {
          margin: 0 0 20px 0;
          padding: 0 0 0 15px;
          font-size: 16px;
          list-style-type: none;
          li {
            line-height: 30px;
            display: flex;
            span:first-child {
              margin: 0 19px 0 1px;
              text-align: right;
              color: #989393;
            }
            span:last-child {
              flex: 1;
              word-break: break-all;
            }
          }
        }
        .part-time-detail-job-info {
          font-size: 16px;
          line-height: 29px;
          p {
            margin-bottom: 20px;
          }
        }
      }
    }
    .part-time-detail-right {
      margin-left: 20px;
      .enterprise {
        padding: 20px;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
        .enterprise-img {
          display: flex;
          justify-content: center;
          margin-bottom: 20px;
          padding: 0 0 20px 0;
          img {
            width: 115px;
            height: 115px;
          }
        }
        .enterprise-name {
          font-size: 25px;
          text-align: center;
          color: #3b3b3b;
        }
        .enterprise-info-list {
          padding: 0 50px;
          list-style: none;
          font-size: 16px;
          li {
            display: flex;
            span:first-child {
              margin: 0 5px 0 0;
              text-align: right;
              color: #989393;
            }
            span:last-child {
              flex: 1;
            }
          }
        }
      }
      .recommend-part-time-list {
        margin-top: 20px;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
        h5 {
          margin: 0;
          padding: 10px 20px;
          border-bottom: 1px solid #dadada;
          font-size: 15px;
          font-weight: normal;
        }
        ul {
          list-style-type: none;
          margin: 0;
          padding: 0;
          li {
            border-bottom: 1px solid #dadada;
            a {
              padding: 20px;
              display: block;
              transition: background-color .15s, border-color .15s;
              p {
                margin-bottom: 15px;
                font-size: 13px;
              }
              p:last-child {
                margin-bottom: 0;
              }
              .part-time-name {
                font-size: 15px;
                color: #3b3b3b;
              }
              .part-time-city {
                display: flex;
                justify-content: space-between;
                color: #666;
              }
              .part-time-price {
                color: #f56c6c;
              }
            }
            a:hover {
              background-color: #ecf5ff;
            }
          }
          li:last-child {
            border-bottom: 0;
          }
        }
      }
    }
  }
</style>
