import { Button } from '@/components/ui/button';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { CheckCircle, Lightbulb, Shield, Users, ArrowRight } from 'lucide-react';
import { LeadCaptureForm } from './LeadCaptureForm';
import { analytics } from '@/lib/analytics';

export function CreatorsSection() {
  return (
    <section id="creators" className="py-24 bg-white">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-4xl md:text-5xl font-bold text-gray-900 mb-6">
            From "Vibe" to{' '}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-green-600 to-blue-600">
              Viable Product
            </span>
            , Seamlessly.
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Turn your vision into reality without the technical headaches. We bridge the gap between your ideas and expert execution.
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8 mb-16">
          <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105">
            <CardHeader className="text-center pb-4">
              <div className="w-16 h-16 bg-gradient-to-br from-blue-500 to-purple-600 rounded-2xl flex items-center justify-center mx-auto mb-4">
                <Lightbulb className="h-8 w-8 text-white" />
              </div>
              <CardTitle className="text-xl font-bold text-gray-900">
                Translate Your Vision
              </CardTitle>
            </CardHeader>
            <CardContent className="text-center">
              <p className="text-gray-600 leading-relaxed">
                Our "Project Scoping Wizard" turns your ideas into a clear, actionable brief that developers understand. No tech jargon required.
              </p>
            </CardContent>
          </Card>

          <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105">
            <CardHeader className="text-center pb-4">
              <div className="w-16 h-16 bg-gradient-to-br from-green-500 to-blue-600 rounded-2xl flex items-center justify-center mx-auto mb-4">
                <Users className="h-8 w-8 text-white" />
              </div>
              <CardTitle className="text-xl font-bold text-gray-900">
                Access Vetted Experts
              </CardTitle>
            </CardHeader>
            <CardContent className="text-center">
              <p className="text-gray-600 leading-relaxed">
                Connect with "Finishers"—top-tier developers vetted for their product sense, communication, and ability to deliver.
              </p>
            </CardContent>
          </Card>

          <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105">
            <CardHeader className="text-center pb-4">
              <div className="w-16 h-16 bg-gradient-to-br from-purple-500 to-pink-600 rounded-2xl flex items-center justify-center mx-auto mb-4">
                <Shield className="h-8 w-8 text-white" />
              </div>
              <CardTitle className="text-xl font-bold text-gray-900">
                Hire with Confidence
              </CardTitle>
            </CardHeader>
            <CardContent className="text-center">
              <p className="text-gray-600 leading-relaxed">
                Our milestone-based escrow system ensures you only pay for work you approve. Your intellectual property is always protected.
              </p>
            </CardContent>
          </Card>
        </div>

        {/* Testimonial */}
        <div className="bg-gradient-to-r from-blue-50 to-purple-50 rounded-3xl p-8 md:p-12 mb-12">
          <div className="max-w-4xl mx-auto">
            <div className="flex items-center mb-6">
              <img 
                src="https://images.pexels.com/photos/3785077/pexels-photo-3785077.jpeg?auto=compress&cs=tinysrgb&w=150&h=150&fit=crop&crop=face" 
                alt="Vivian Chen" 
                className="w-16 h-16 rounded-full object-cover mr-4"
              />
              <div>
                <h4 className="font-semibold text-gray-900">Vivian Chen</h4>
                <p className="text-gray-600">Founder, StyleSync</p>
              </div>
            </div>
            <blockquote className="text-xl md:text-2xl text-gray-700 italic leading-relaxed">
              "I had a detailed Figma design but no idea how to explain the backend. 'handoff' translated my vision and connected me with a developer who just 'got it'. My MVP was live in weeks."
            </blockquote>
            <div className="flex items-center mt-6">
              {[...Array(5)].map((_, i) => (
                <CheckCircle key={i} className="h-5 w-5 text-green-500 mr-1" />
              ))}
              <span className="ml-2 text-gray-600 font-medium">Verified Success Story</span>
            </div>
          </div>
        </div>

        <div className="grid md:grid-cols-2 gap-8 items-start">
          <div className="text-center md:text-left">
            <Button 
              size="lg" 
              className="bg-gradient-to-r from-blue-600 to-purple-600 hover:from-blue-700 hover:to-purple-700 text-white px-8 py-4 text-lg font-semibold rounded-xl shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105"
              onClick={() => analytics.buttonClick('bring_project_to_life', 'creators_section')}
            >
              Bring Your Project to Life
              <ArrowRight className="ml-2 h-5 w-5" />
            </Button>
          </div>
          <LeadCaptureForm 
            type="creator"
            title="Ready to Get Started?"
            description="Join our waitlist and be among the first to access handoff when we launch."
          />
        </div>
      </div>
    </section>
  );
}
