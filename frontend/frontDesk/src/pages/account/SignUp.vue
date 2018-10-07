<template>
  <section class="sign-up-container">
    <el-tabs class="tab" v-model="signUpType" :stretch="true">
      <el-tab-pane label="个人用户注册" name="profile">
        <el-form :model="profileForm" :rules="profileFormRules" ref="profileForm" label-width="128px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="profileForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="profileForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitProfileForm('profileForm')">立即注册</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="企业用户注册" name="enterprise">
        <el-form :model="enterpriseForm" :rules="enterpriseFormRules" ref="enterpriseForm" label-width="128px">
          <el-form-item label="企业名称" prop="enterpriseName">
            <el-input v-model="enterpriseForm.enterpriseName"></el-input>
          </el-form-item>
          <el-form-item label="行业类型" prop="industryType">
            <el-cascader
              :options="industryTypeList"
              v-model="enterpriseForm.industryType">
            </el-cascader>
          </el-form-item>
          <el-form-item label="人员规模" prop="staffSize">
            <el-select v-model="enterpriseForm.staffSize">
              <el-option label="1 - 50人" value="1"></el-option>
              <el-option label="51 - 100人" value="2"></el-option>
              <el-option label="101 - 200人" value="3"></el-option>
              <el-option label="201 - 500人" value="4"></el-option>
              <el-option label="501 - 1000人" value="5"></el-option>
              <el-option label="1001及以上" value="6"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="管理员用户名" prop="username">
            <el-input v-model="enterpriseForm.username"></el-input>
          </el-form-item>
          <el-form-item label="管理员密码" prop="password">
            <el-input v-model="enterpriseForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitEnterpriseForm('enterpriseForm')">立即注册</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </section>
</template>

<script>
export default {
  name: 'SignUp',
  data () {
    return {
      signUpType: this.$route.query.type !== undefined ? this.$route.query.type : 'profile',
      profileForm: {
        username: '',
        password: ''
      },
      profileFormRules: {
        username: [
          { required: true, message: '用户名由3-20个字符组成', trigger: 'change' },
          { min: 3, max: 20, message: '用户名由3-20个字符组成', trigger: 'change' }
        ],
        password: [
          { required: true, message: '密码由3-20个字符组成', trigger: 'change' },
          { min: 3, max: 20, message: '密码由3-20个字符组成', trigger: 'change' }
        ]
      },
      enterpriseForm: {},
      enterpriseFormRules: {
        enterpriseName: [
          { required: true, message: '企业名称由1-30个中文、英文、数字及合法字符组成', trigger: 'change' },
          { min: 1, max: 30, message: '企业名称由1-30个中文、英文、数字及合法字符组成', trigger: 'change' }
        ],
        industryType: [
          { required: true, message: '请选择所属行业', trigger: 'change' }
        ],
        staffSize: [
          { required: true, message: '请选择人员规模', trigger: 'change' }
        ],
        username: [
          { required: true, message: '用户名由3-20个字符组成', trigger: 'change' },
          { min: 3, max: 20, message: '用户名由3-20个字符组成', trigger: 'change' }
        ],
        password: [
          { required: true, message: '密码由3-20个字符组成', trigger: 'change' },
          { min: 3, max: 20, message: '密码由3-20个字符组成', trigger: 'change' }
        ]
      },
      industryTypeList: [
        {
          value: '100',
          label: 'IT服务',
          children: [
            { value: '101', label: '计算机软件/硬件/信息服务' },
            { value: '102', label: '互联网和相关服务' },
            { value: '103', label: '其他' }
          ]
        },
        {
          value: '200',
          label: '批发/零售',
          children: [
            { value: '201', label: '服装/纺织' },
            { value: '202', label: '超市/便利店/百货商场' },
            { value: '203', label: '食品/饮料' },
            { value: '204', label: '家具/家纺' },
            { value: '205', label: '日用品/化妆品' },
            { value: '206', label: '家电/数码' },
            { value: '207', label: '烟酒' },
            { value: '208', label: '文教/工美/体育/娱乐用品' },
            { value: '209', label: '批发' },
            { value: '210', label: '其他' }
          ]
        },
        {
          value: '300',
          label: '制造业',
          children: []
        },
        {
          value: '400',
          label: '生活服务',
          children: []
        },
        {
          value: '500',
          label: '文化/体育/娱乐业',
          children: []
        },
        {
          value: '600',
          label: '建筑/房地产',
          children: []
        },
        {
          value: '700',
          label: '教育',
          children: []
        },
        {
          value: '800',
          label: '运输/物流/仓储',
          children: []
        },
        {
          value: '900',
          label: '医疗',
          children: []
        },
        {
          value: '1000',
          label: '政府',
          children: []
        },
        {
          value: '1100',
          label: '金融',
          children: []
        },
        {
          value: '1200',
          label: '能源/采矿',
          children: []
        },
        {
          value: '1300',
          label: '农林渔牧',
          children: []
        },
        {
          value: '1500',
          label: '其他'
        }
      ]
    };
  },
  methods: {
    submitProfileForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit p!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    submitEnterpriseForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit e!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  }
};
</script>

<style lang="scss">

</style>
