import { FinishersSection } from '../components/FinishersSection.tsx';
import { Card, CardContent, CardHeader, CardTitle } from '../components/ui/card.tsx';
import { Badge } from '../components/ui/badge.tsx';
import { Button } from '../components/ui/button.tsx';
import { CheckCircle, ArrowRight, Code, DollarSign, Heart, Shield } from 'lucide-react';
import { FinalCTASection } from '../components/FinalCTASection.tsx';
import { useEffect } from 'react';
import { analytics } from '../lib/analytics.ts';

export function Finishers() {
  useEffect(() => {
    analytics.pageView('finishers');
  }, []);

  return (
    <>
      {/* Page Hero */}
      <section className="pt-24 pb-16 bg-gradient-to-br from-orange-50 via-white to-red-50">
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <h1 className="text-4xl md:text-6xl font-bold text-gray-900 mb-6">
            Skip the Proposals.{' '}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-orange-600 to-red-600">
              Finish Meaningful Projects.
            </span>
          </h1>
          <p className="text-xl text-gray-600 max-w-2xl mx-auto mb-8">
            Work on exciting, well-defined projects with passionate creators. Be a technical partner, not just a hired hand.
          </p>
          <Badge variant="secondary" className="px-4 py-2 text-sm">
            💰 Competitive 10% commission + Fair project rates
          </Badge>
        </div>
      </section>

      <FinishersSection />

      {/* Vetting Process */}
      <section className="py-24 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-4xl font-bold text-gray-900 mb-6">
              Our Rigorous Vetting Process
            </h2>
            <p className="text-xl text-gray-600 max-w-3xl mx-auto">
              We only accept the top 5% of developers. Here's how we ensure quality.
            </p>
          </div>

          <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-8">
            <Card className="border-0 shadow-lg text-center">
              <CardHeader className="pb-4">
                <div className="w-12 h-12 bg-blue-100 rounded-full flex items-center justify-center mx-auto mb-4">
                  <Code className="h-6 w-6 text-blue-600" />
                </div>
                <CardTitle className="text-lg">Technical Review</CardTitle>
              </CardHeader>
              <CardContent>
                <p className="text-gray-600 text-sm">
                  Portfolio review, code assessment, and technical interview to validate your skills.
                </p>
              </CardContent>
            </Card>

            <Card className="border-0 shadow-lg text-center">
              <CardHeader className="pb-4">
                <div className="w-12 h-12 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-4">
                  <Heart className="h-6 w-6 text-green-600" />
                </div>
                <CardTitle className="text-lg">Product Sense</CardTitle>
              </CardHeader>
              <CardContent>
                <p className="text-gray-600 text-sm">
                  We evaluate your ability to understand user needs and build products that matter.
                </p>
              </CardContent>
            </Card>

            <Card className="border-0 shadow-lg text-center">
              <CardHeader className="pb-4">
                <div className="w-12 h-12 bg-purple-100 rounded-full flex items-center justify-center mx-auto mb-4">
                  <CheckCircle className="h-6 w-6 text-purple-600" />
                </div>
                <CardTitle className="text-lg">Communication</CardTitle>
              </CardHeader>
              <CardContent>
                <p className="text-gray-600 text-sm">
                  Mock client scenarios to assess your ability to explain technical concepts clearly.
                </p>
              </CardContent>
            </Card>

            <Card className="border-0 shadow-lg text-center">
              <CardHeader className="pb-4">
                <div className="w-12 h-12 bg-orange-100 rounded-full flex items-center justify-center mx-auto mb-4">
                  <Shield className="h-6 w-6 text-orange-600" />
                </div>
                <CardTitle className="text-lg">Background Check</CardTitle>
              </CardHeader>
              <CardContent>
                <p className="text-gray-600 text-sm">
                  Reference checks and verification to ensure professionalism and reliability.
                </p>
              </CardContent>
            </Card>
          </div>
        </div>
      </section>

      {/* Benefits for Finishers */}
      <section className="py-24 bg-gray-50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-4xl font-bold text-gray-900 mb-6">
              Why Top Developers Choose handoff
            </h2>
            <p className="text-xl text-gray-600">
              Focus on what you love: building great products.
            </p>
          </div>

          <div className="grid md:grid-cols-2 gap-12 items-center">
            <div>
              <div className="space-y-8">
                <div className="flex items-start space-x-4">
                  <div className="w-8 h-8 bg-green-100 rounded-full flex items-center justify-center flex-shrink-0 mt-1">
                    <CheckCircle className="h-5 w-5 text-green-600" />
                  </div>
                  <div>
                    <h3 className="font-semibold text-gray-900 mb-2">No More Endless Proposals</h3>
                    <p className="text-gray-600">
                      Projects come pre-scoped with clear requirements. Skip the bidding wars and proposal writing.
                    </p>
                  </div>
                </div>

                <div className="flex items-start space-x-4">
                  <div className="w-8 h-8 bg-blue-100 rounded-full flex items-center justify-center flex-shrink-0 mt-1">
                    <DollarSign className="h-5 w-5 text-blue-600" />
                  </div>
                  <div>
                    <h3 className="font-semibold text-gray-900 mb-2">Fair & Transparent Rates</h3>
                    <p className="text-gray-600">
                      Competitive project rates with our low 10% commission. No hidden fees or surprise deductions.
                    </p>
                  </div>
                </div>

                <div className="flex items-start space-x-4">
                  <div className="w-8 h-8 bg-purple-100 rounded-full flex items-center justify-center flex-shrink-0 mt-1">
                    <Heart className="h-5 w-5 text-purple-600" />
                  </div>
                  <div>
                    <h3 className="font-semibold text-gray-900 mb-2">Passionate Creators</h3>
                    <p className="text-gray-600">
                      Work with creators who have a clear vision and are invested in building something meaningful.
                    </p>
                  </div>
                </div>

                <div className="flex items-start space-x-4">
                  <div className="w-8 h-8 bg-orange-100 rounded-full flex items-center justify-center flex-shrink-0 mt-1">
                    <Shield className="h-5 w-5 text-orange-600" />
                  </div>
                  <div>
                    <h3 className="font-semibold text-gray-900 mb-2">Secure Payments</h3>
                    <p className="text-gray-600">
                      Our escrow system ensures you get paid on time, every time. No more chasing invoices.
                    </p>
                  </div>
                </div>
              </div>
            </div>

            <div className="bg-gradient-to-br from-orange-100 to-red-100 rounded-2xl p-8">
              <div className="text-center">
                <h3 className="text-2xl font-bold text-gray-900 mb-4">
                  Average Finisher Earnings
                </h3>
                <div className="space-y-4">
                  <div className="bg-white rounded-lg p-4">
                    <div className="text-3xl font-bold text-gray-900">$8,500</div>
                    <div className="text-gray-600">Monthly earnings</div>
                  </div>
                  <div className="grid grid-cols-2 gap-4">
                    <div className="bg-white rounded-lg p-3">
                      <div className="text-xl font-bold text-gray-900">4.9⭐</div>
                      <div className="text-sm text-gray-600">Avg rating</div>
                    </div>
                    <div className="bg-white rounded-lg p-3">
                      <div className="text-xl font-bold text-gray-900">12</div>
                      <div className="text-sm text-gray-600">Projects/year</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Application Requirements */}
      <section className="py-24 bg-white">
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-3xl font-bold text-gray-900 mb-6">
              Ready to Apply?
            </h2>
            <p className="text-xl text-gray-600">
              Make sure you meet our requirements before applying.
            </p>
          </div>

          <Card className="border-0 shadow-lg">
            <CardHeader>
              <CardTitle className="text-center text-2xl">Application Requirements</CardTitle>
            </CardHeader>
            <CardContent className="p-6">
              <div className="grid md:grid-cols-2 gap-6">
                <div>
                  <h4 className="font-semibold text-gray-900 mb-3">Technical Requirements</h4>
                  <ul className="space-y-2 text-sm text-gray-600">
                    <li className="flex items-center">
                      <CheckCircle className="h-4 w-4 text-green-500 mr-2 flex-shrink-0" />
                      3+ years professional development experience
                    </li>
                    <li className="flex items-center">
                      <CheckCircle className="h-4 w-4 text-green-500 mr-2 flex-shrink-0" />
                      Portfolio of completed projects
                    </li>
                    <li className="flex items-center">
                      <CheckCircle className="h-4 w-4 text-green-500 mr-2 flex-shrink-0" />
                      Expertise in modern frameworks/languages
                    </li>
                    <li className="flex items-center">
                      <CheckCircle className="h-4 w-4 text-green-500 mr-2 flex-shrink-0" />
                      Understanding of full-stack development
                    </li>
                  </ul>
                </div>
                <div>
                  <h4 className="font-semibold text-gray-900 mb-3">Soft Skills Requirements</h4>
                  <ul className="space-y-2 text-sm text-gray-600">
                    <li className="flex items-center">
                      <CheckCircle className="h-4 w-4 text-green-500 mr-2 flex-shrink-0" />
                      Excellent English communication
                    </li>
                    <li className="flex items-center">
                      <CheckCircle className="h-4 w-4 text-green-500 mr-2 flex-shrink-0" />
                      Product thinking and user empathy
                    </li>
                    <li className="flex items-center">
                      <CheckCircle className="h-4 w-4 text-green-500 mr-2 flex-shrink-0" />
                      Reliable and deadline-oriented
                    </li>
                    <li className="flex items-center">
                      <CheckCircle className="h-4 w-4 text-green-500 mr-2 flex-shrink-0" />
                      Available 20+ hours per week
                    </li>
                  </ul>
                </div>
              </div>
              
              <div className="text-center mt-8">
                <Button 
                  size="lg" 
                  className="bg-gradient-to-r from-orange-600 to-red-600 hover:from-orange-700 hover:to-red-700"
                  onClick={() => analytics.buttonClick('apply_now_detailed', 'finishers_page')}
                >
                  Apply to Become a Finisher
                  <ArrowRight className="ml-2 h-5 w-5" />
                </Button>
                <p className="text-sm text-gray-500 mt-2">
                  Application takes ~15 minutes • Response within 48 hours
                </p>
              </div>
            </CardContent>
          </Card>
        </div>
      </section>

      <FinalCTASection />
    </>
  );
}