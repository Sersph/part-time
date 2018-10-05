<template>
  <section class="select-city-container">
    <el-dialog title="切换城市" :visible.sync="selectCityContainerVisibleFlag" type="text">
      <el-form :model="selectCityForm" :rules="selectCityFormRules" ref="selectCityForm">
        <el-row>
          <el-col :span="12">
            <el-form-item prop="provinceId">
              <el-select class="province" placeholder="省份" filterable v-model="selectCityForm.provinceId">
                <el-option
                  v-for="(provinceItem, index) in provinceList"
                  :key="index"
                  :value="provinceItem.id"
                  :label="provinceItem.name"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="cityId">
              <el-select class="city" placeholder="城市" filterable v-model="selectCityForm.cityId">
                <el-option
                  v-for="(cityItem, index) in currentOptionCityList"
                  :key="index"
                  :value="cityItem.id"
                  :label="cityItem.name"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="selectCityContainerVisibleFlag = false">取 消</el-button>
        <el-button type="primary" @click="confirm">确 定</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'SelectCity',
  data () {
    return {
      selectCityContainerVisibleFlag: false,
      selectCityForm: {},
      currentOptionCityList: [],
      selectCityFormRules: {
        provinceId: [
          { required: true, message: '请选择省份', trigger: 'change' }
        ],
        cityId: [
          { required: true, message: '请选择城市', trigger: 'change' }
        ]
      }
    };
  },
  computed: {
    ...mapState('partTime', [
      'partTimeSearchCondition'
    ]),
    ...mapState('location', [
      'regionList',
      'provinceList'
    ])
  },
  watch: {
    'selectCityForm.provinceId': {
      handler (provinceId) {
        delete this.selectCityForm['cityId'];
        this.currentOptionCityList = this.regionList.filter(item => item.parentId === provinceId);
      }
    }
  },
  mounted () {
    this.initRegionList();
  },
  methods: {
    ...mapActions('location', [
      'initRegionList'
    ]),
    confirm () {
      this.$refs['selectCityForm'].validate((valid) => {
        if (valid) {
          // url 更新兼职搜索区域
          this.$router.push({
            path: this.$router.pah,
            query: {
              cityId: this.selectCityForm.cityId
            }
          });
          // 关闭区域选择框
          this.selectCityContainerVisibleFlag = false;
          // 清空表单
          this.$refs['selectCityForm'].resetFields();
        }
      });
    }
  }
};
</script>

<style lang="scss">
  .select-city-container {
    .el-col-12:first-child {
      padding-right: 10px;
    }
    .el-col-12:last-child {
      padding-left: 10px;
    }
  }
</style>
