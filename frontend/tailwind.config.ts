import type { Config } from "tailwindcss";
import * as tailwind_animate from "tailwindcss-animate";

const config : Config = {
  content: ['./src/**/*.{ts,tsx}'],
  theme: {
    extend: {},
  },
  plugins: [tailwind_animate],
}

export default config;
