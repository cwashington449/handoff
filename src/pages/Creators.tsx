import { CreatorsSection } from '../components/CreatorsSection';
import { HowItWorksSection } from '../components/HowItWorksSection';
import { Card, CardContent, CardHeader, CardTitle } from '../components/ui/card';
import { Badge } from '../components/ui/badge';
import { Button } from '../components/ui/button';
import { CheckCircle, ArrowRight, Star, TrendingUp, Users, Zap } from 'lucide-react';
import { FinalCTASection } from '../components/FinalCTASection';
import { useEffect } from 'react';
import { analytics } from '../lib/analytics';

export function Creators() {
  useEffect(() => {
    analytics.pageView('creators');
  }, []);

  return (
    <>
      {/* Page Hero */}
      <section className="pt-24 pb-16 bg-gradient-to-br from-blue-50 via-white to-purple-50">
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <h1 className="text-4xl md:text-6xl font-bold text-gray-900 mb-6">
            Turn Your{' '}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-blue-600 to-purple-600">
              Vision Into Reality
            </span>
          </h1>
          <p className="text-xl text-gray-600 max-w-2xl mx-auto mb-8">
            Connect with vetted developers who specialize in turning creative visions into finished products. 
            No more technical headaches, just results.
          </p>
          <Badge variant="secondary" className="px-4 py-2 text-sm">
            ⚡ Average project completion: 4-8 weeks
          </Badge>
        </div>
      </section>

      <CreatorsSection />
      <HowItWorksSection />

      {/* Success Stories Section */}
      <section className="py-24 bg-gray-50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <h2 className="text-4xl font-bold text-gray-900 mb-6">
              Creator Success Stories
            </h2>
            <p className="text-xl text-gray-600">
              Real projects, real results from creators like you.
            </p>
          </div>

          <div className="grid md:grid-cols-2 gap-8 mb-16">
            {/* Case Study 1 */}
            <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300">
              <CardHeader>
                <div className="flex items-center justify-between mb-4">
                  <Badge className="bg-green-100 text-green-800">E-commerce</Badge>
                  <div className="flex items-center">
                    {[...Array(5)].map((_, i) => (
                      <Star key={i} className="h-4 w-4 text-yellow-500 fill-current" />
                    ))}
                  </div>
                </div>
                <CardTitle className="text-xl font-bold text-gray-900">
                  FitTrack: Fitness Subscription Platform
                </CardTitle>
              </CardHeader>
              <CardContent>
                <div className="mb-4">
                  <img 
                    src="https://images.pexels.com/photos/3768916/pexels-photo-3768916.jpeg?auto=compress&cs=tinysrgb&w=400&h=200&fit=crop" 
                    alt="FitTrack Platform" 
                    className="w-full h-32 object-cover rounded-lg"
                  />
                </div>
                <p className="text-gray-600 mb-4">
                  <strong>Challenge:</strong> Non-technical founder with detailed wireframes needed a full-stack subscription platform with payment processing.
                </p>
                <p className="text-gray-600 mb-4">
                  <strong>Solution:</strong> Connected with a React/Node.js specialist who delivered a complete MVP with Stripe integration in 5 weeks.
                </p>
                <div className="flex items-center justify-between">
                  <div className="flex items-center space-x-4 text-sm text-gray-500">
                    <div className="flex items-center">
                      <TrendingUp className="h-4 w-4 mr-1" />
                      $50K ARR
                    </div>
                    <div className="flex items-center">
                      <Users className="h-4 w-4 mr-1" />
                      2K+ Users
                    </div>
                  </div>
                </div>
              </CardContent>
            </Card>

            {/* Case Study 2 */}
            <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300">
              <CardHeader>
                <div className="flex items-center justify-between mb-4">
                  <Badge className="bg-blue-100 text-blue-800">SaaS</Badge>
                  <div className="flex items-center">
                    {[...Array(5)].map((_, i) => (
                      <Star key={i} className="h-4 w-4 text-yellow-500 fill-current" />
                    ))}
                  </div>
                </div>
                <CardTitle className="text-xl font-bold text-gray-900">
                  TaskFlow: Project Management Tool
                </CardTitle>
              </CardHeader>
              <CardContent>
                <div className="mb-4">
                  <img 
                    src="https://images.pexels.com/photos/3184291/pexels-photo-3184291.jpeg?auto=compress&cs=tinysrgb&w=400&h=200&fit=crop" 
                    alt="TaskFlow Dashboard" 
                    className="w-full h-32 object-cover rounded-lg"
                  />
                </div>
                <p className="text-gray-600 mb-4">
                  <strong>Challenge:</strong> Product manager wanted to build a simplified project management tool for small teams.
                </p>
                <p className="text-gray-600 mb-4">
                  <strong>Solution:</strong> Matched with a Vue.js/Python developer who created a clean, intuitive interface with real-time features.
                </p>
                <div className="flex items-center justify-between">
                  <div className="flex items-center space-x-4 text-sm text-gray-500">
                    <div className="flex items-center">
                      <Zap className="h-4 w-4 mr-1" />
                      Launch Ready
                    </div>
                    <div className="flex items-center">
                      <Users className="h-4 w-4 mr-1" />
                      Beta Testing
                    </div>
                  </div>
                </div>
              </CardContent>
            </Card>
          </div>

          {/* What Makes Us Different */}
          <div className="bg-white rounded-3xl p-8 md:p-12">
            <div className="text-center mb-12">
              <h3 className="text-2xl font-bold text-gray-900 mb-4">
                Why Creators Choose handoff
              </h3>
              <p className="text-gray-600 max-w-2xl mx-auto">
                We're not just another freelance platform. We're a specialized marketplace for turning visions into viable products.
              </p>
            </div>
            
            <div className="grid md:grid-cols-3 gap-8">
              <div className="text-center">
                <div className="w-12 h-12 bg-blue-100 rounded-full flex items-center justify-center mx-auto mb-4">
                  <CheckCircle className="h-6 w-6 text-blue-600" />
                </div>
                <h4 className="font-semibold text-gray-900 mb-2">No Tech Jargon</h4>
                <p className="text-gray-600 text-sm">
                  Our Project Scoping Wizard translates your vision into clear technical requirements without the confusion.
                </p>
              </div>
              
              <div className="text-center">
                <div className="w-12 h-12 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-4">
                  <Users className="h-6 w-6 text-green-600" />
                </div>
                <h4 className="font-semibold text-gray-900 mb-2">Vetted Finishers</h4>
                <p className="text-gray-600 text-sm">
                  Every developer is rigorously vetted for technical skills, communication, and product sense. Only top 5% accepted.
                </p>
              </div>
              
              <div className="text-center">
                <div className="w-12 h-12 bg-purple-100 rounded-full flex items-center justify-center mx-auto mb-4">
                  <Zap className="h-6 w-6 text-purple-600" />
                </div>
                <h4 className="font-semibold text-gray-900 mb-2">Results Focused</h4>
                <p className="text-gray-600 text-sm">
                  Milestone-based payments and our success guarantee ensure you get exactly what you envisioned.
                </p>
              </div>
            </div>

            <div className="text-center mt-12">
              <Button 
                size="lg" 
                className="bg-gradient-to-r from-blue-600 to-purple-600 hover:from-blue-700 hover:to-purple-700"
                onClick={() => analytics.buttonClick('start_your_project', 'creators_page')}
              >
                Start Your Project Today
                <ArrowRight className="ml-2 h-5 w-5" />
              </Button>
            </div>
          </div>
        </div>
      </section>

      <FinalCTASection />
    </>
  );
}