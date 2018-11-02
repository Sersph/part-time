<template>
  <section class="personal-part-time-container">
    <transition name="slide-bottom" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'PersonalPartTime'
};
</script>

<style lang="scss">

</style>
