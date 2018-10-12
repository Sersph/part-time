<template>
  <section class="personal-container">
    <personal-nav/>
    <transition name="slide-bottom" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
import PersonalNav from '@/components/account/personal/PersonalNav';

export default {
  name: 'Persona',
  components: {
    PersonalNav
  }
};
</script>

<style lang="scss">
  .personal-container {
    display: flex;
    & > section:last-child {
      flex: 1;
    }
  }
</style>
