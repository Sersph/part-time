<template>
  <section class="profile-container">
    <profile-nav/>
    <transition name="opacity" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
import ProfileNav from '@/components/account/ProfileNav';
export default {
  name: 'Profile',
  components: {
    ProfileNav
  },
  userType: 0
};
</script>

<style lang="scss">
  .profile-container {
    display: flex;
    & > section:last-child {
      flex: 1;
    }
  }
</style>
