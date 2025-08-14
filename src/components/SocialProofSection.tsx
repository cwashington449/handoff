import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { Button } from '@/components/ui/button';
import { ExternalLink, Star, TrendingUp, Users, Zap } from 'lucide-react';

export function SocialProofSection() {
  return (
    <section id="case-studies" className="py-24 bg-gray-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-4xl md:text-5xl font-bold text-gray-900 mb-6">
            Success Stories &{' '}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-green-600 to-blue-600">
              Social Proof
            </span>
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Real projects, real results. See how handoff has helped creators and finishers achieve their goals.
          </p>
        </div>

        {/* Stats */}
        <div className="grid grid-cols-2 md:grid-cols-4 gap-8 mb-16">
          <div className="text-center">
            <div className="text-4xl font-bold text-blue-600 mb-2">500+</div>
            <div className="text-gray-600">Projects Completed</div>
          </div>
          <div className="text-center">
            <div className="text-4xl font-bold text-green-600 mb-2">98%</div>
            <div className="text-gray-600">Success Rate</div>
          </div>
          <div className="text-center">
            <div className="text-4xl font-bold text-purple-600 mb-2">4.9</div>
            <div className="text-gray-600">Average Rating</div>
          </div>
          <div className="text-center">
            <div className="text-4xl font-bold text-orange-600 mb-2">6 weeks</div>
            <div className="text-gray-600">Avg. Completion</div>
          </div>
        </div>

        {/* Case Studies */}
        <div className="grid md:grid-cols-2 gap-8 mb-16">
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
                <strong>Challenge:</strong> Non-technical founder with detailed wireframes needed a full-stack subscription platform with payment processing and user management.
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
                <Button variant="outline" size="sm">
                  View Case Study
                  <ExternalLink className="ml-2 h-4 w-4" />
                </Button>
              </div>
            </CardContent>
          </Card>

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
                <strong>Challenge:</strong> Product manager with enterprise experience wanted to build a simplified project management tool for small teams.
              </p>
              <p className="text-gray-600 mb-4">
                <strong>Solution:</strong> Matched with a Vue.js/Python developer who created a clean, intuitive interface with real-time collaboration features.
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
                <Button variant="outline" size="sm">
                  View Case Study
                  <ExternalLink className="ml-2 h-4 w-4" />
                </Button>
              </div>
            </CardContent>
          </Card>
        </div>

        {/* Testimonials Grid */}
        <div className="grid md:grid-cols-3 gap-8 mb-16">
          <Card className="border-0 shadow-lg bg-white">
            <CardContent className="p-6">
              <div className="flex items-center mb-4">
                <img 
                  src="https://images.pexels.com/photos/3785077/pexels-photo-3785077.jpeg?auto=compress&cs=tinysrgb&w=60&h=60&fit=crop&crop=face" 
                  alt="Sarah Kim" 
                  className="w-12 h-12 rounded-full object-cover mr-3"
                />
                <div>
                  <h4 className="font-semibold text-gray-900">Sarah Kim</h4>
                  <p className="text-sm text-gray-600">Startup Founder</p>
                </div>
              </div>
              <p className="text-gray-700 italic">
                "The scoping process was incredible. I finally felt understood by a developer. My app launched exactly as I envisioned."
              </p>
              <div className="flex items-center mt-4">
                {[...Array(5)].map((_, i) => (
                  <Star key={i} className="h-4 w-4 text-yellow-500 fill-current" />
                ))}
              </div>
            </CardContent>
          </Card>

          <Card className="border-0 shadow-lg bg-white">
            <CardContent className="p-6">
              <div className="flex items-center mb-4">
                <img 
                  src="https://images.pexels.com/photos/2379004/pexels-photo-2379004.jpeg?auto=compress&cs=tinysrgb&w=60&h=60&fit=crop&crop=face" 
                  alt="Marcus Johnson" 
                  className="w-12 h-12 rounded-full object-cover mr-3"
                />
                <div>
                  <h4 className="font-semibold text-gray-900">Marcus Johnson</h4>
                  <p className="text-sm text-gray-600">Full-Stack Developer</p>
                </div>
              </div>
              <p className="text-gray-700 italic">
                "Finally, projects with clear requirements and passionate clients. The escrow system gives me confidence to do my best work."
              </p>
              <div className="flex items-center mt-4">
                {[...Array(5)].map((_, i) => (
                  <Star key={i} className="h-4 w-4 text-yellow-500 fill-current" />
                ))}
              </div>
            </CardContent>
          </Card>

          <Card className="border-0 shadow-lg bg-white">
            <CardContent className="p-6">
              <div className="flex items-center mb-4">
                <img 
                  src="https://images.pexels.com/photos/3763188/pexels-photo-3763188.jpeg?auto=compress&cs=tinysrgb&w=60&h=60&fit=crop&crop=face" 
                  alt="Elena Rodriguez" 
                  className="w-12 h-12 rounded-full object-cover mr-3"
                />
                <div>
                  <h4 className="font-semibold text-gray-900">Elena Rodriguez</h4>
                  <p className="text-sm text-gray-600">Product Designer</p>
                </div>
              </div>
              <p className="text-gray-700 italic">
                "I had the designs but needed someone to bring them to life. handoff connected me with the perfect developer partner."
              </p>
              <div className="flex items-center mt-4">
                {[...Array(5)].map((_, i) => (
                  <Star key={i} className="h-4 w-4 text-yellow-500 fill-current" />
                ))}
              </div>
            </CardContent>
          </Card>
        </div>

        {/* As Seen On */}
        <div className="text-center">
          <h3 className="text-lg font-semibold text-gray-600 mb-8">As Featured In</h3>
          <div className="flex flex-wrap justify-center items-center gap-8 opacity-60">
            <div className="text-2xl font-bold text-gray-400">TechCrunch</div>
            <div className="text-2xl font-bold text-gray-400">Product Hunt</div>
            <div className="text-2xl font-bold text-gray-400">Indie Hackers</div>
            <div className="text-2xl font-bold text-gray-400">Y Combinator</div>
            <div className="text-2xl font-bold text-gray-400">Hacker News</div>
          </div>
        </div>
      </div>
    </section>
  );
}
