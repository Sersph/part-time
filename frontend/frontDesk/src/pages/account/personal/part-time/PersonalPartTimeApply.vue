<template>
  <section class="personal-part-time-apply-container">
    <transition name="slide-bottom" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'PersonalPartTimeApply'
};
</script>

<style lang="scss">

</style>
