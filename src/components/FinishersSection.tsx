import { Button } from '@/components/ui/button';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { FileText, Heart, DollarSign, ArrowRight, Star } from 'lucide-react';

export function FinishersSection() {
  return (
    <section id="finishers" className="py-24 bg-gray-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-4xl md:text-5xl font-bold text-gray-900 mb-6">
            Skip the Proposals.{' '}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-orange-600 to-red-600">
              Finish Meaningful Projects.
            </span>
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Work on exciting, well-defined projects with passionate creators. Be a technical partner, not just a hired hand.
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8 mb-16">
          <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105">
            <CardHeader className="text-center pb-4">
              <div className="w-16 h-16 bg-gradient-to-br from-orange-500 to-red-600 rounded-2xl flex items-center justify-center mx-auto mb-4">
                <FileText className="h-8 w-8 text-white" />
              </div>
              <CardTitle className="text-xl font-bold text-gray-900">
                Well-Defined Scopes
              </CardTitle>
            </CardHeader>
            <CardContent className="text-center">
              <p className="text-gray-600 leading-relaxed">
                No more vague requirements. Start with a clear "Handoff Brief" that outlines the vision, goals, and existing assets.
              </p>
            </CardContent>
          </Card>

          <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105">
            <CardHeader className="text-center pb-4">
              <div className="w-16 h-16 bg-gradient-to-br from-purple-500 to-pink-600 rounded-2xl flex items-center justify-center mx-auto mb-4">
                <Heart className="h-8 w-8 text-white" />
              </div>
              <CardTitle className="text-xl font-bold text-gray-900">
                Partner with Visionaries
              </CardTitle>
            </CardHeader>
            <CardContent className="text-center">
              <p className="text-gray-600 leading-relaxed">
                Work with passionate creators on exciting MVPs and innovative products. Be a technical partner, not just a hired hand.
              </p>
            </CardContent>
          </Card>

          <Card className="border-0 shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105">
            <CardHeader className="text-center pb-4">
              <div className="w-16 h-16 bg-gradient-to-br from-green-500 to-blue-600 rounded-2xl flex items-center justify-center mx-auto mb-4">
                <DollarSign className="h-8 w-8 text-white" />
              </div>
              <CardTitle className="text-xl font-bold text-gray-900">
                Fair & Transparent
              </CardTitle>
            </CardHeader>
            <CardContent className="text-center">
              <p className="text-gray-600 leading-relaxed">
                Our competitive 10% commission and secure escrow system mean you're rewarded fairly for your expertise.
              </p>
            </CardContent>
          </Card>
        </div>

        {/* Tech Stack Showcase */}
        <div className="bg-white rounded-3xl p-8 md:p-12 mb-12 shadow-lg">
          <h3 className="text-2xl font-bold text-gray-900 text-center mb-8">
            Work with Technologies You Love
          </h3>
          <div className="flex flex-wrap justify-center gap-4 mb-8">
            {['React', 'Node.js', 'Python', 'TypeScript', 'Next.js', 'Vue.js', 'Django', 'PostgreSQL', 'MongoDB', 'AWS', 'Docker', 'GraphQL'].map((tech) => (
              <Badge key={tech} variant="secondary" className="px-4 py-2 text-sm font-medium bg-gray-100 hover:bg-gray-200 transition-colors">
                {tech}
              </Badge>
            ))}
          </div>
        </div>

        {/* Testimonial */}
        <div className="bg-gradient-to-r from-orange-50 to-red-50 rounded-3xl p-8 md:p-12 mb-12">
          <div className="max-w-4xl mx-auto">
            <div className="flex items-center mb-6">
              <img 
                src="https://images.pexels.com/photos/2379004/pexels-photo-2379004.jpeg?auto=compress&cs=tinysrgb&w=150&h=150&fit=crop&crop=face" 
                alt="Felix Rodriguez" 
                className="w-16 h-16 rounded-full object-cover mr-4"
              />
              <div>
                <h4 className="font-semibold text-gray-900">Felix Rodriguez</h4>
                <p className="text-gray-600">Full-Stack Developer</p>
              </div>
            </div>
            <blockquote className="text-xl md:text-2xl text-gray-700 italic leading-relaxed">
              "'handoff' is a game-changer. I get to work on interesting, pre-vetted projects without the endless back-and-forth on scope. It's the best way to find high-quality freelance work."
            </blockquote>
            <div className="flex items-center mt-6">
              {[...Array(5)].map((_, i) => (
                <Star key={i} className="h-5 w-5 text-yellow-500 mr-1 fill-current" />
              ))}
              <span className="ml-2 text-gray-600 font-medium">5.0 Rating • 47 Projects Completed</span>
            </div>
          </div>
        </div>

        <div className="text-center">
          <Button 
            size="lg" 
            className="bg-gradient-to-r from-orange-600 to-red-600 hover:from-orange-700 hover:to-red-700 text-white px-8 py-4 text-lg font-semibold rounded-xl shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105"
          >
            Apply to Become a Finisher
            <ArrowRight className="ml-2 h-5 w-5" />
          </Button>
        </div>
      </div>
    </section>
  );
}
