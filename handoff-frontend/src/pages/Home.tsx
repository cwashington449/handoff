import { HeroSection } from '../components/HeroSection.tsx';
import { HowItWorksSection } from '../components/HowItWorksSection.tsx';
import { SocialProofSection } from '../components/SocialProofSection.tsx';
import { FinalCTASection } from '../components/FinalCTASection.tsx';
import { useEffect } from 'react';
import { analytics } from '../lib/analytics.ts';

export function Home() {
  useEffect(() => {
    analytics.pageView('home');
  }, []);

  return (
    <>
      <HeroSection />
      <HowItWorksSection />
      <SocialProofSection />
      <FinalCTASection />
    </>
  );
}