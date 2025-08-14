import { HeroSection } from '../components/HeroSection';
import { HowItWorksSection } from '../components/HowItWorksSection';
import { SocialProofSection } from '../components/SocialProofSection';
import { FinalCTASection } from '../components/FinalCTASection';
import { useEffect } from 'react';
import { analytics } from '../lib/analytics';

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