import type { Config } from "tailwindcss";
import tailwind_animate from "tailwindcss-animate";
import tailwind_scrollbar from "tailwind-scrollbar";

const config : Config = {
  content: ['./src/**/*.{ts,tsx}'],
  theme: {
    extend: {},
  },
  plugins: [
    tailwind_animate,
    tailwind_scrollbar({
      preferredStrategy: 'pseudoelements',
      nocompatible: true
    })
  ],
}

export default config;
