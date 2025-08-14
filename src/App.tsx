import { HeroSection } from './components/HeroSection';
import { CreatorsSection } from './components/CreatorsSection';
import { FinishersSection } from './components/FinishersSection';
import { HowItWorksSection } from './components/HowItWorksSection';
import { SocialProofSection } from './components/SocialProofSection';
import { FinalCTASection } from './components/FinalCTASection';
import { Footer } from './components/Footer';
import { Navigation } from './components/Navigation';

function App() {
  return (
    <div className="min-h-screen bg-white">
      <Navigation />
      <HeroSection />
      <CreatorsSection />
      <FinishersSection />
      <HowItWorksSection />
      <SocialProofSection />
      <FinalCTASection />
      <Footer />
    </div>
  );
}

export default App;
