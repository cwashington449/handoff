import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { ArrowRight, FileSearch, Users, Rocket } from 'lucide-react';

export function HowItWorksSection() {
  return (
    <section id="how-it-works" className="py-24 bg-white">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-4xl md:text-5xl font-bold text-gray-900 mb-6">
            How{' '}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-blue-600 to-purple-600">
              handoff
            </span>{' '}
            Works
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            A simple, transparent process that gets your project from idea to reality in three clear steps.
          </p>
        </div>

        <div className="relative">
          {/* Connection Lines */}
          <div className="hidden lg:block absolute top-1/2 left-1/4 right-1/4 h-0.5 bg-gradient-to-r from-blue-200 via-purple-200 to-green-200 transform -translate-y-1/2"></div>
          
          <div className="grid md:grid-cols-3 gap-8 relative">
            <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105 relative">
              <div className="absolute -top-4 left-1/2 transform -translate-x-1/2 w-8 h-8 bg-blue-600 text-white rounded-full flex items-center justify-center font-bold text-sm">
                1
              </div>
              <CardHeader className="text-center pt-8 pb-4">
                <div className="w-16 h-16 bg-gradient-to-br from-blue-500 to-blue-600 rounded-2xl flex items-center justify-center mx-auto mb-4">
                  <FileSearch className="h-8 w-8 text-white" />
                </div>
                <CardTitle className="text-xl font-bold text-gray-900">
                  Creator Defines the Vision
                </CardTitle>
              </CardHeader>
              <CardContent className="text-center">
                <p className="text-gray-600 leading-relaxed">
                  Use our "Project Scoping Wizard" to create a detailed "Handoff Brief" that captures your vision, requirements, and goals.
                </p>
              </CardContent>
            </Card>

            <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105 relative">
              <div className="absolute -top-4 left-1/2 transform -translate-x-1/2 w-8 h-8 bg-purple-600 text-white rounded-full flex items-center justify-center font-bold text-sm">
                2
              </div>
              <CardHeader className="text-center pt-8 pb-4">
                <div className="w-16 h-16 bg-gradient-to-br from-purple-500 to-purple-600 rounded-2xl flex items-center justify-center mx-auto mb-4">
                  <Users className="h-8 w-8 text-white" />
                </div>
                <CardTitle className="text-xl font-bold text-gray-900">
                  Finisher Scopes the Project
                </CardTitle>
              </CardHeader>
              <CardContent className="text-center">
                <p className="text-gray-600 leading-relaxed">
                  A vetted "Finisher" proposes a paid "Scoping Session" to create a full project roadmap with timelines and milestones.
                </p>
              </CardContent>
            </Card>

            <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105 relative">
              <div className="absolute -top-4 left-1/2 transform -translate-x-1/2 w-8 h-8 bg-green-600 text-white rounded-full flex items-center justify-center font-bold text-sm">
                3
              </div>
              <CardHeader className="text-center pt-8 pb-4">
                <div className="w-16 h-16 bg-gradient-to-br from-green-500 to-green-600 rounded-2xl flex items-center justify-center mx-auto mb-4">
                  <Rocket className="h-8 w-8 text-white" />
                </div>
                <CardTitle className="text-xl font-bold text-gray-900">
                  Build & Complete with Confidence
                </CardTitle>
              </CardHeader>
              <CardContent className="text-center">
                <p className="text-gray-600 leading-relaxed">
                  Work through milestones with our secure escrow system, turning your vision into a finished product with regular updates.
                </p>
              </CardContent>
            </Card>
          </div>
        </div>

        {/* Process Benefits */}
        <div className="mt-16 bg-gradient-to-r from-gray-50 to-blue-50 rounded-3xl p-8 md:p-12">
          <div className="grid md:grid-cols-2 gap-8 items-center">
            <div>
              <h3 className="text-2xl font-bold text-gray-900 mb-6">
                Why This Process Works
              </h3>
              <div className="space-y-4">
                <div className="flex items-start">
                  <div className="w-6 h-6 bg-green-500 rounded-full flex items-center justify-center mr-3 mt-0.5">
                    <ArrowRight className="h-3 w-3 text-white" />
                  </div>
                  <div>
                    <h4 className="font-semibold text-gray-900">Clear Communication</h4>
                    <p className="text-gray-600">No more lost-in-translation moments between vision and execution.</p>
                  </div>
                </div>
                <div className="flex items-start">
                  <div className="w-6 h-6 bg-green-500 rounded-full flex items-center justify-center mr-3 mt-0.5">
                    <ArrowRight className="h-3 w-3 text-white" />
                  </div>
                  <div>
                    <h4 className="font-semibold text-gray-900">Risk Mitigation</h4>
                    <p className="text-gray-600">Milestone-based payments protect both creators and finishers.</p>
                  </div>
                </div>
                <div className="flex items-start">
                  <div className="w-6 h-6 bg-green-500 rounded-full flex items-center justify-center mr-3 mt-0.5">
                    <ArrowRight className="h-3 w-3 text-white" />
                  </div>
                  <div>
                    <h4 className="font-semibold text-gray-900">Quality Assurance</h4>
                    <p className="text-gray-600">Every finisher is vetted for technical skills and communication.</p>
                  </div>
                </div>
              </div>
            </div>
            <div className="relative">
              <div className="aspect-square bg-gradient-to-br from-blue-100 to-purple-100 rounded-2xl flex items-center justify-center">
                <div className="text-center">
                  <div className="w-20 h-20 bg-white rounded-full flex items-center justify-center shadow-lg mb-4 mx-auto">
                    <Rocket className="h-10 w-10 text-blue-600" />
                  </div>
                  <p className="text-gray-700 font-medium">Average project completion: 4-8 weeks</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
