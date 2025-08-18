import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card.tsx';
import { Button } from '@/components/ui/button.tsx';
import { Badge } from '@/components/ui/badge.tsx';
import { CheckCircle, ArrowRight, Shield, Zap, Heart } from 'lucide-react';
import { analytics } from '@/lib/analytics.ts';

export function PricingSection() {
  const handlePricingClick = (plan: string) => {
    analytics.buttonClick(`pricing_${plan}`, 'pricing_section');
  };

  return (
    <section id="pricing" className="py-24 bg-white">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-4xl md:text-5xl font-bold text-gray-900 mb-6">
            Simple,{' '}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-green-600 to-blue-600">
              Transparent Pricing
            </span>
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            No hidden fees, no surprises. Only pay when your project succeeds.
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8 mb-16">
          {/* Scoping Session */}
          <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300">
            <CardHeader className="text-center pb-4">
              <div className="w-16 h-16 bg-gradient-to-br from-blue-500 to-blue-600 rounded-2xl flex items-center justify-center mx-auto mb-4">
                <Zap className="h-8 w-8 text-white" />
              </div>
              <CardTitle className="text-xl font-bold text-gray-900">
                Scoping Session
              </CardTitle>
              <div className="text-4xl font-bold text-gray-900 mt-4">
                $199
                <span className="text-lg text-gray-600 font-normal">/session</span>
              </div>
            </CardHeader>
            <CardContent className="text-center">
              <p className="text-gray-600 mb-6">
                Get your project professionally scoped before development begins
              </p>
              <ul className="text-left space-y-3 mb-8">
                <li className="flex items-start">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-3 mt-0.5 flex-shrink-0" />
                  <span className="text-gray-700">Detailed project roadmap & timeline</span>
                </li>
                <li className="flex items-start">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-3 mt-0.5 flex-shrink-0" />
                  <span className="text-gray-700">Technology stack recommendations</span>
                </li>
                <li className="flex items-start">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-3 mt-0.5 flex-shrink-0" />
                  <span className="text-gray-700">Fixed-price quote for development</span>
                </li>
                <li className="flex items-start">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-3 mt-0.5 flex-shrink-0" />
                  <span className="text-gray-700">Risk assessment & mitigation</span>
                </li>
              </ul>
              <Button 
                variant="outline" 
                className="w-full border-2 border-blue-600 text-blue-600 hover:bg-blue-600 hover:text-white transition-all"
                onClick={() => handlePricingClick('scoping')}
              >
                Book Scoping Session
              </Button>
            </CardContent>
          </Card>

          {/* Project Development */}
          <Card className="border-2 border-green-500 shadow-xl hover:shadow-2xl transition-all duration-300 relative">
            <div className="absolute -top-4 left-1/2 transform -translate-x-1/2">
              <Badge className="bg-green-600 text-white px-4 py-1">Most Popular</Badge>
            </div>
            <CardHeader className="text-center pb-4 pt-8">
              <div className="w-16 h-16 bg-gradient-to-br from-green-500 to-green-600 rounded-2xl flex items-center justify-center mx-auto mb-4">
                <Heart className="h-8 w-8 text-white" />
              </div>
              <CardTitle className="text-xl font-bold text-gray-900">
                Project Development
              </CardTitle>
              <div className="text-4xl font-bold text-gray-900 mt-4">
                10%
                <span className="text-lg text-gray-600 font-normal"> commission</span>
              </div>
            </CardHeader>
            <CardContent className="text-center">
              <p className="text-gray-600 mb-6">
                Secure, milestone-based development with full protection
              </p>
              <ul className="text-left space-y-3 mb-8">
                <li className="flex items-start">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-3 mt-0.5 flex-shrink-0" />
                  <span className="text-gray-700">Secure escrow payment system</span>
                </li>
                <li className="flex items-start">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-3 mt-0.5 flex-shrink-0" />
                  <span className="text-gray-700">Milestone-based payment releases</span>
                </li>
                <li className="flex items-start">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-3 mt-0.5 flex-shrink-0" />
                  <span className="text-gray-700">Quality assurance & code review</span>
                </li>
                <li className="flex items-start">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-3 mt-0.5 flex-shrink-0" />
                  <span className="text-gray-700">Ongoing project management</span>
                </li>
              </ul>
              <Button 
                className="w-full bg-green-600 hover:bg-green-700"
                onClick={() => handlePricingClick('development')}
              >
                Start Development
                <ArrowRight className="ml-2 h-4 w-4" />
              </Button>
            </CardContent>
          </Card>

          {/* Success Guarantee */}
          <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300">
            <CardHeader className="text-center pb-4">
              <div className="w-16 h-16 bg-gradient-to-br from-purple-500 to-purple-600 rounded-2xl flex items-center justify-center mx-auto mb-4">
                <Shield className="h-8 w-8 text-white" />
              </div>
              <CardTitle className="text-xl font-bold text-gray-900">
                Success Guarantee
              </CardTitle>
              <div className="text-4xl font-bold text-gray-900 mt-4">
                Free
                <span className="text-lg text-gray-600 font-normal"> protection</span>
              </div>
            </CardHeader>
            <CardContent className="text-center">
              <p className="text-gray-600 mb-6">
                Your success is guaranteed with our comprehensive protection
              </p>
              <ul className="text-left space-y-3 mb-8">
                <li className="flex items-start">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-3 mt-0.5 flex-shrink-0" />
                  <span className="text-gray-700">Money-back satisfaction guarantee</span>
                </li>
                <li className="flex items-start">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-3 mt-0.5 flex-shrink-0" />
                  <span className="text-gray-700">24/7 customer support</span>
                </li>
                <li className="flex items-start">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-3 mt-0.5 flex-shrink-0" />
                  <span className="text-gray-700">IP protection & NDA coverage</span>
                </li>
                <li className="flex items-start">
                  <CheckCircle className="h-5 w-5 text-green-500 mr-3 mt-0.5 flex-shrink-0" />
                  <span className="text-gray-700">Post-launch maintenance support</span>
                </li>
              </ul>
              <Button 
                variant="outline" 
                className="w-full border-2 border-purple-600 text-purple-600 hover:bg-purple-600 hover:text-white transition-all"
                onClick={() => handlePricingClick('guarantee')}
              >
                Learn More
              </Button>
            </CardContent>
          </Card>
        </div>

        {/* Pricing Benefits */}
        <div className="bg-gradient-to-r from-gray-50 to-blue-50 rounded-3xl p-8 md:p-12">
          <div className="text-center mb-8">
            <h3 className="text-2xl font-bold text-gray-900 mb-4">
              Why Our Pricing Model Works
            </h3>
            <p className="text-gray-600 max-w-2xl mx-auto">
              We only succeed when you succeed. Our aligned incentives ensure quality results.
            </p>
          </div>
          
          <div className="grid md:grid-cols-3 gap-8">
            <div className="text-center">
              <div className="w-12 h-12 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-4">
                <CheckCircle className="h-6 w-6 text-green-600" />
              </div>
              <h4 className="font-semibold text-gray-900 mb-2">No Upfront Risk</h4>
              <p className="text-gray-600 text-sm">
                Start with a low-cost scoping session to validate your project before committing to full development.
              </p>
            </div>
            
            <div className="text-center">
              <div className="w-12 h-12 bg-blue-100 rounded-full flex items-center justify-center mx-auto mb-4">
                <Shield className="h-6 w-6 text-blue-600" />
              </div>
              <h4 className="font-semibold text-gray-900 mb-2">Protected Payments</h4>
              <p className="text-gray-600 text-sm">
                Your payments are held in escrow and only released when you approve completed milestones.
              </p>
            </div>
            
            <div className="text-center">
              <div className="w-12 h-12 bg-purple-100 rounded-full flex items-center justify-center mx-auto mb-4">
                <Heart className="h-6 w-6 text-purple-600" />
              </div>
              <h4 className="font-semibold text-gray-900 mb-2">Quality Guarantee</h4>
              <p className="text-gray-600 text-sm">
                If you're not satisfied with the work, we'll make it right or provide a full refund.
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}