<template>
  <section class="personal-setting-container">
    <transition name="slide-bottom" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'PersonalSetting'
};
</script>

<style lang="scss">

</style>
