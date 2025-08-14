import { PricingSection } from '../components/PricingSection';
import { FAQSection } from '../components/FAQSection';
import { FinalCTASection } from '../components/FinalCTASection';
import { useEffect } from 'react';
import { analytics } from '../lib/analytics';

export function Pricing() {
  useEffect(() => {
    analytics.pageView('pricing');
  }, []);

  return (
    <>
      {/* Page Hero */}
      <section className="pt-24 pb-16 bg-gradient-to-br from-blue-50 via-white to-purple-50">
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <h1 className="text-4xl md:text-6xl font-bold text-gray-900 mb-6">
            Simple,{' '}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-green-600 to-blue-600">
              Transparent Pricing
            </span>
          </h1>
          <p className="text-xl text-gray-600 max-w-2xl mx-auto">
            No hidden fees, no surprises. Only pay when your project succeeds. 
            Start with a low-cost scoping session and scale from there.
          </p>
        </div>
      </section>

      <PricingSection />
      <FAQSection />
      <FinalCTASection />
    </>
  );
}