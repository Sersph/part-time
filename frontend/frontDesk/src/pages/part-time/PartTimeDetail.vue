<template>
  <section class="part-time-detail-container">
    <el-breadcrumb class="breadcrumb" separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/' }">深圳兼职招聘</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/' }">派发传单兼职</el-breadcrumb-item>
      <el-breadcrumb-item>轻松无责派单</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row>
      <el-col :span="16">
        <section class="part-time-detail-left">
          <section class="part-time-base-info">
            <p class="title">
              <span>轻松无责派单</span>
            </p>
            <p class="price-info">
              <span class="price">
                <span>100</span>
                <span>元/天</span>
              </span>
              <span class="type">
                <span>月结</span>
                <span>传单派发</span>
              </span>
            </p>
            <p class="apply">
              <el-button type="danger" class="apply">报名参加</el-button>
            </p>
          </section>
          <section class="part-time-detail-info">
            <p class="title">职位详情</p>
            <ul class="part-time-detail-info-list">
              <li>
                <span>招聘人数：</span>
                <span>200</span>
              </li>
              <li>
                <span>兼职类型：</span>
                <span>短期兼职</span>
              </li>
              <li>
                <span>兼职种类：</span>
                <span>传单派发</span>
              </li>
            </ul>
            <p class="title">薪资待遇</p>
            <ul class="part-time-detail-info-list">
              <li>
                <span>基本工资：</span>
                <span>100元/天</span>
              </li>
              <li>
                <span>结算方式：</span>
                <span>月结</span>
              </li>
              <li>
                <span>薪资描述：</span>
                <span>带带我去带我去带我去带我去带我去带我去带我去带我去带我去带我去带我去带我去带我去带我去我去</span>
              </li>
            </ul>
            <p class="title">工作详情</p>
            <ul class="part-time-detail-info-list">
              <li>
                <span>工作地址：</span>
                <span>全国-不限</span>
              </li>
              <li>
                <span>工作日期：</span>
                <span>2018-10-05 至 2018-10-13</span>
              </li>
              <li>
                <span>上班时间：</span>
                <span>18:00 - 5:00</span>
              </li>
              <li>
                <span>工作时间：</span>
                <span>周一 ~ 周二</span>
              </li>
              <li>
                <span>工作描述：</span>
                <span>
                  <div class="part-time-detail-job-info">
                    <p>
                      【一】工作内容
                      1、本次招聘为富士康人资公开免费招聘
                    </p>
                    <p>
                      【一】工作内容
                      1、本次招聘为富士康人资公开免费招聘
                    </p>
                    <p>
                      【一】工作内容
                      1、本次招聘为富士康人资公开免费招聘
                    </p>
                    <p>
                      【一】工作内容
                      1、本次招聘为富士康人资公开免费招聘
                    </p>
                    <p>
                      【一】工作内容
                      1、本次招聘为富士康人资公开免费招聘
                    </p>
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
            <p class="enterprise-name">大吉利公司</p>
            <ul class="enterprise-info-list">
              <li>
                <span>公司地址：</span>
                <span>深圳市罗湖区</span>
              </li>
            </ul>
          </section>
          <section class="recommend-part-time-list">
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
  </section>
</template>

<script>
export default {
  name: 'PartTimeDetail',
  beforeCreate() {
    document.documentElement.scrollTop = 0;
  }
};
</script>

<style lang="scss">
  .part-time-detail-container {
    max-width: 1100px !important;
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
          font-size: 20px;
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
              margin-left: 31px;
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
      box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
      .enterprise {
        padding: 20px;
        box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
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
          text-align: left;
          color: #3b3b3b;
        }
        .enterprise-info-list {
          list-style: none;
          padding: 0;
          font-size: 16px;
          span:first-child {
            margin: 0 19px 0 0;
            text-align: right;
            color: #989393;
          }
        }
      }
      .recommend-part-time-list {
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
