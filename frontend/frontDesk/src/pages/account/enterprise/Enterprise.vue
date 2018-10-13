<template>
  <section class="enterprise-container">
    <enterprise-nav/>
    <breadcrumb/>
    <transition name="slide-bottom" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
import EnterpriseNav from '@/components/account/enterprise/EnterpriseNav';
import Breadcrumb from '@/components/account/Breadcrumb';

export default {
  name: 'Enterprise',
  components: {
    EnterpriseNav,
    Breadcrumb
  }
};
</script>

<style lang="scss">
  .enterprise-container {
    display: flex;
    .breadcrumb-container {
      display: flex;
      align-items: center;
      position: absolute;
      left: 252px;
      right: 0;
      height: 56px;
      padding-left: 25px;
      box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
    }
    & > section:last-child {
      flex: 1;
      & > section:first-child {
        padding: 81px 30px;
      }
    }
  }
</style>
