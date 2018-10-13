<template>
  <section class="enterprise-part-time-post-operation-container">
    <el-form :model="partTimeForm" :rules="partTimeFormRules" ref="partTimeForm" label-width="100px">
      <el-row>
        <el-col :span="24">
          <el-form-item label="兼职标题" prop="name">
            <el-input v-model="partTimeForm.name"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="招聘人数" prop="recruitmentCount">
            <el-input type="number" v-model="partTimeForm.recruitmentCount"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="兼职类型" prop="partTimeTypeId">
            <el-select v-model="partTimeForm.partTimeTypeId" placeholder="请选择">
              <el-option
                v-for="(partTimeTypeItem, index) in partTimeBaseInfo.partTimeTypeList"
                :key="index"
                :label="partTimeTypeItem.name"
                :value="partTimeTypeItem.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="兼职种类" prop="partTimeSpeciesId">
            <el-select v-model="partTimeForm.partTimeSpeciesId" placeholder="请选择">
              <el-option
                v-for="(partTimeSpeciesItem, index) in partTimeBaseInfo.partTimeSpeciesList"
                :key="index"
                :label="partTimeSpeciesItem.name"
                :value="partTimeSpeciesItem.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <div class="linear"></div>
      <el-row>
        <el-col :span="12">
          <el-form-item label="薪资" prop="price">
            <el-input type="number" v-model="partTimeForm.price"></el-input>
            <span class="price-tooltip">元 / </span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item prop="calculationTypeId">
            <el-select v-model="partTimeForm.calculationTypeId" placeholder="请选择">
              <el-option
                v-for="(calculationTypeItem, index) in partTimeBaseInfo.calculationTypeList"
                :key="index"
                :label="calculationTypeItem.name"
                :value="calculationTypeItem.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="结算类型" prop="settlementTypeId">
            <el-select v-model="partTimeForm.settlementTypeId" placeholder="请选择">
              <el-option
                v-for="(settlementItem, index) in partTimeBaseInfo.settlementTypeList"
                :key="index"
                :label="settlementItem.name"
                :value="settlementItem.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="薪资描述" prop="priceDescription">
            <el-input v-model="partTimeForm.priceDescription"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <div class="linear"></div>
      <el-row>
        <el-col :span="12">
          <el-form-item label="工作地址" prop="regionIds">
            <el-cascader
              :options="regionList"
              :props="{value: 'id', label: 'name'}"
              v-model="partTimeForm.regionIds">
            </el-cascader>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="详细地址" prop="detailAddress">
            <el-input v-model="partTimeForm.detailAddress"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="工作日期" prop="workingDate">
            <el-date-picker
              v-model="partTimeForm.workingDate"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="上班时间" prop="workingTime">
            <el-time-picker
              is-range
              v-model="partTimeForm.workingTime"
              range-separator="至"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
              placeholder="选择时间范围">
            </el-time-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="工作时间" prop="workingTimeWeek">
            <el-checkbox-group v-model="partTimeForm.workingTimeWeek" size="small">
              <el-checkbox-button v-for="workingTimeWeekItem in workingTimeWeekList"
                                  :key="workingTimeWeekItem.id"
                                  :label="workingTimeWeekItem.id"
              >{{workingTimeWeekItem.name}}
              </el-checkbox-button>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="工作描述" prop="jobDescription">
        <el-input type="textarea" v-model="partTimeForm.jobDescription"></el-input>
      </el-form-item>
      <div class="linear"></div>
      <el-row>
        <el-col :span="12">
          <el-form-item label="联系人" prop="contactPeople">
            <el-input v-model="partTimeForm.contactPeople"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="联系电话" prop="contactPhone">
            <el-input v-model="partTimeForm.contactPhone"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button type="primary" :loading="doPartTimeSaveFormLoading" @click="doPartTimeSaveForm('partTimeForm')">发布
        </el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import NProgress from 'nprogress';
import api from '@/api';

export default {
  name: 'EnterprisePartTimePostOperation',
  data () {
    return {
      doPartTimeSaveFormLoading: false,
      partTimeForm: {
        name: '123',
        recruitmentCount: '12',
        partTimeTypeId: 1,
        partTimeSpeciesId: 1,
        price: '1',
        calculationTypeId: 1,
        settlementTypeId: 1,
        priceDescription: 1,
        regionIds: [440000, 440100, 440103],
        detailAddress: '1',
        workingDate: [new Date(2333, 1, 1, 9, 0), new Date(2333, 1, 1, 18, 0)],
        workingTime: [new Date(2000, 1, 1, 9, 0), new Date(2000, 1, 1, 18, 0)],
        workingTimeWeek: [1],
        jobDescription: '1',
        contactPeople: '1',
        contactPhone: '2312312'
      },
      partTimeFormRules: {
        name: [
          { required: true, message: '兼职标题由1-30个字符组成', trigger: 'change' },
          { min: 1, max: 30, message: '兼职标题由1-30个字符组成', trigger: 'change' }
        ],
        recruitmentCount: [
          { required: true, message: '请输入招聘人数', trigger: 'change' },
          { min: 1, max: 5, message: '招聘人数最多5位数字', trigger: 'change' }
        ],
        partTimeTypeId: [
          { required: true, message: '请选择兼职类型', trigger: 'change' }
        ],
        partTimeSpeciesId: [
          { required: true, message: '请选择兼职种类', trigger: 'change' }
        ],
        price: [
          { required: true, message: '请输入薪资', trigger: 'change' },
          { min: 1, max: 11, message: '薪资最多11位数字', trigger: 'change' }
        ],
        calculationTypeId: [
          { required: true, message: '请选择资新类型', trigger: 'change' }
        ],
        settlementTypeId: [
          { required: true, message: '请选择结算类型', trigger: 'change' }
        ],
        priceDescription: [],
        regionIds: [
          { required: true, message: '请选择工作地址', trigger: 'change' }
        ],
        detailAddress: [
          { required: true, message: '请输入详细地址', trigger: 'change' },
          { min: 1, max: 50, message: '详细地址由1-50位字符组成', trigger: 'change' }
        ],
        workingDate: [
          { required: true, message: '请选择工作日期', trigger: 'change' }
        ],
        workingTime: [
          { required: true, message: '请选择上班时间', trigger: 'change' }
        ],
        workingTimeWeek: [
          { required: true, message: '请选择工作时间', trigger: 'change' }
        ],
        jobDescription: [
          { required: true, message: '请输入工作描述', trigger: 'change' },
          { min: 1, max: 500, message: '工作描述1-500位字符组成', trigger: 'change' }
        ],
        contactPeople: [
          { required: true, message: '请输入联系人', trigger: 'change' },
          { min: 1, max: 50, message: '联系人由1-20位字符组成', trigger: 'change' }
        ],
        contactPhone: [
          { required: true, message: '请输入联系人电话', trigger: 'change' },
          { min: 5, max: 20, message: '详细电话由5-20位数字组成', trigger: 'change' }
        ]
      },
      workingTimeWeekList: [
        { id: 1, name: '周一' },
        { id: 2, name: '周二' },
        { id: 3, name: '周三' },
        { id: 4, name: '周四' },
        { id: 5, name: '周五' },
        { id: 6, name: '周六' },
        { id: 7, name: '周日' }
      ]
    };
  },
  computed: {
    ...mapState('partTime', [
      'partTimeBaseInfo'
    ]),
    ...mapState('location', [
      'regionList'
    ])
  },
  beforeCreate () {
    document.documentElement.scrollTop = 0;
  },
  methods: {
    ...mapActions('partTime', [
      'asyncInitPartTimeBaseInfo'
    ]),
    doPartTimeSaveForm (formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          const data = {
            name: this.partTimeForm.name,
            recruitmentCount: this.partTimeForm.recruitmentCount,
            partTimeTypeId: this.partTimeForm.partTimeTypeId,
            partTimeSpeciesId: this.partTimeForm.partTimeSpeciesId,
            price: this.partTimeForm.price,
            calculationTypeId: this.partTimeForm.calculationTypeId,
            settlementTypeId: this.partTimeForm.settlementTypeId,
            priceDescription: this.partTimeForm.priceDescription,
            provinceId: this.partTimeForm.regionIds[0],
            cityId: this.partTimeForm.regionIds[1],
            areaId: this.partTimeForm.regionIds[2],
            detailAddress: this.partTimeForm.detailAddress,
            workingDateStart: this.partTimeForm.workingDate[0].getTime() / 1000,
            workingDateEnd: this.partTimeForm.workingDate[1].getTime() / 1000,
            workingTime:
              this.partTimeForm.workingTime[0].getHours() + ':' + this.partTimeForm.workingTime[0].getMinutes() + ':' + this.partTimeForm.workingTime[0].getSeconds() +
              '-' +
              this.partTimeForm.workingTime[1].getHours() + ':' + this.partTimeForm.workingTime[1].getMinutes() + ':' + this.partTimeForm.workingTime[1].getSeconds(),
            workingTimeWeek: this.partTimeForm.workingTimeWeek.join(','),
            jobDescription: this.partTimeForm.jobDescription,
            contactPeople: this.partTimeForm.contactPeople,
            contactPhone: this.partTimeForm.contactPhone
          };

          this.doPartTimeSaveFormLoading = true;
          NProgress.start();
          const result = await api.partTime.partTimeAdd(data);
          if (result.code === 0) {
            // 保存成功逻辑
            this.$notify({
              message: '发布成功',
              position: 'bottom-left',
              duration: 1500,
              showClose: false
            });
            NProgress.done();
            // 跳转首页
            setTimeout(() => {
              this.$router.replace('/account/enterprise/partTime/partTimePost/list');
              document.documentElement.scrollTop = 0;
            }, 500);
          } else {
            NProgress.done();
            this.doPartTimeSaveFormLoading = false;
            // 保存失败逻辑
            this.$notify({
              message: result.message,
              position: 'bottom-left',
              duration: 2000,
              showClose: false
            });
          }
        } else {
          return false;
        }
      });
    }
  }
};
</script>

<style lang="scss">
  .enterprise-part-time-post-operation-container {
    color: #606266;
    .el-form {
      overflow: hidden;
      max-width: 800px;
      min-width: 600px;
      margin-left: auto;
      margin-right: auto;
    }
    .linear {
      width: 100%;
      border-bottom: 3px dashed #ddd;
      margin: 0 0 31px 100px;
    }
    .price-tooltip {
      position: absolute;
      right: -60px;
    }
    textarea {
      min-height: 200px !important;
    }
    .el-checkbox-group {
      display: flex;
      .el-checkbox-button {
        flex: 1;
        .el-checkbox-button__inner {
          width: 100%;
        }
      }
    }
  }
</style>
